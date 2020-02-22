## 周志内容（2.10-2.14）

### 文件管理系统
#### 1.数据库表设计
```sql
create table oa_system.attachment_info
(
    id          bigint auto_increment comment '附件编号'
        primary key,
    file_id     bigint                             not null comment '附件所属文档',
    location    varchar(50)                        not null comment '附件所属文档',
    name        varchar(100)                       null comment '附件名称',
    type        varchar(10)                        not null comment '附件属性',
    create_uesr varchar(20)                        not null comment '附件名称',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    remarks     mediumtext                         null comment '备注',
    is_delete   int      default 0                 null comment '是否删除 0-未删除 1-删除'
)
    comment '附件';

create table file_info
(
    id          bigint auto_increment comment '文档编号'
        primary key,
    type        varchar(10)                        not null comment '文档属性',
    location    varchar(50)                        not null comment '文档位置',
    name        varchar(100)                       null comment '文档名称',
    group_id    int                                null comment '部门id',
    create_user varchar(20)                        not null comment '文档创建者',
    create_time datetime default CURRENT_TIMESTAMP not null comment '文档创建时间',
    remarks     mediumtext                         null comment '备注',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '文档更新时间',
    is_delete   int      default 0                 null comment '是否删除 0-未删除 1-删除'
)
    comment '文档基本信息';

create table sys_log
(
    id             int auto_increment comment '日志Id'
        primary key,
    oper_model     varchar(20)                        null comment '操作模块',
    oper_type      varchar(20)                        null comment '操作类型',
    oper_desc      varchar(50)                        null comment '操作描述',
    oper_user_id   int                                null comment '操作人id',
    oper_user_name varchar(50)                        null comment '操作人姓名',
    oper_user_ip   varchar(100)                       null comment '操作人ip',
    oper_time      datetime default CURRENT_TIMESTAMP null,
    oper_file_id   int                                null comment '操作的附件或文档id'
)
    comment '系统操作日志表';


```
#### 2.接口设计
##### 2.1 文档管理模块 
```java
 /**
     * 查询该部门下的所有文档
     *
     * @param groupId
     * @return
     */
    List<FileInfo> queryAll(Integer groupId);

    /**
     * 新增文档
     *
     * @param fileInfo
     * @return
     */
    ResultDTO<Integer> addFile(FileInfo fileInfo);


    /**
     * 修改文档
     *
     * @param fileInfo
     * @return
     */
    ResultDTO<Integer> updateFile(FileInfo fileInfo);

    /**
     * 删除文档
     *
     * @param fileId
     * @return
     */
    ResultDTO<Integer> deleteFile(Integer fileId, Integer isDelete);

    /**
     * 查询文档
     *
     * @param fileQueryDTO
     * @return
     */
    ResultDTO<List<FileInfo>> queryFile(FileQueryDTO fileQueryDTO);
```
##### 2.2 附件管理模块 
```java
 /**
     * 查询所有附件
     *
     * @return 附件信息
     */
    ResultDTO<List<AttachmentInfo>> queryAllAttachmentInfo(Integer fileId);

    /**
     * 新增附件
     *
     * @param attachmentInfo
     * @return
     */
    ResultDTO<Integer> addAttachment(AttachmentInfo attachmentInfo);

    /**
     * 删除附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> deleteAttachment(Integer attachmentId, Integer isDelete);
```

##### 2.3 回收站管理模块
```java

    /**
     * 还原附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> returnAttachment(Integer attachmentId, Integer isDelete);

    /**
     * 还原文件
     *
     * @param fileId
     * @param isDelete
     * @return
     */
    ResultDTO<Integer> returnFile(Integer fileId, Integer isDelete);

    /**
     * 彻底删除文档
     *
     * @param fileId
     * @return
     */
    ResultDTO<Integer> trashFile(Integer fileId);

    /**
     * 彻底删除附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> trashAttachment(Integer attachmentId);
``` 

##### 2.4 操作日志模块
自定义注解实现对文件操作的日志记录
* 注解类
    ```java
    package com.oasystem.annotation;
    
    import java.lang.annotation.*;
    
    /**
     *
     */
    @Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
    @Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
    @Documented
    public @interface OperLog {
        // 操作模块
        String operModul() default "";
    
        // 操作类型
        String operType() default "";
    
        // 操作说明
        String operDesc() default "";
    }
    
    ```
  
* Aop部分代码
    ```java
    package com.oasystem.aop;
    
    import com.alibaba.fastjson.JSON;
    import com.oasystem.ResultDTO;
    import com.oasystem.annotation.OperLog;
    import com.oasystem.constant.OperLogConstant;
    import com.oasystem.constant.OperLogEnum;
    import com.oasystem.model.SysLog;
    import com.oasystem.service.sysLog.SysLogService;
    import com.oasystem.utils.IpUtil;
    import org.apache.commons.lang3.StringUtils;
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.AfterReturning;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Pointcut;
    import org.aspectj.lang.reflect.MethodSignature;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import org.springframework.web.context.request.RequestAttributes;
    import org.springframework.web.context.request.RequestContextHolder;
    
    import javax.servlet.http.HttpServletRequest;
    import java.lang.reflect.Method;
    import java.util.HashMap;
    import java.util.Map;
    
    
    @Aspect
    @Component
    public class OperLogAspect {
    
    
        @Autowired
        private SysLogService sysLogService;
    
    
        /**
         * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
         */
        @Pointcut("@annotation(com.oasystem.annotation.OperLog)")
        public void operLogPoinCut() {
        }
    
        /**
         * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
         */
        @Pointcut("execution(* com.oasystem.controller..*.*(..))")
        public void operExceptionLogPoinCut() {
        }
    
        /**
         * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
         *
         * @param joinPoint 切入点
         * @param keys      返回结果
         */
        @AfterReturning(value = "operLogPoinCut()", returning = "keys")
        public void saveOperLog(JoinPoint joinPoint, Object keys) {
            // 获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes
                    .resolveReference(RequestAttributes.REFERENCE_REQUEST);
    
            SysLog sysLog = new SysLog();
            try {
                // 从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                // 获取切入点所在的方法
                Method method = signature.getMethod();
                System.out.println(JSON.toJSONString(keys));
                // 获取操作
                OperLog opLog = method.getAnnotation(OperLog.class);
                String code = opLog.operType();
                ResultDTO<Integer> resultDTO = (ResultDTO<Integer>) keys;
                sysLog.setOperFileId(resultDTO.getData());
                if (opLog != null) {
                    // 操作模块
                    sysLog.setOperModel(opLog.operModul());
                    // 操作类型
                    sysLog.setOperType(OperLogEnum.msg(code));
                    // 操作描述
                    sysLog.setOperDesc(opLog.operDesc());
                }
                // TODO 暂时写死 等待登录完成 
                sysLog.setOperUserId(11);
                // TODO 暂时写死 等待登录完成 
                sysLog.setOperUserName("zs");
                // 请求IP
                sysLog.setOperUserIp(IpUtil.getIpAddr(request));
                // 从切面织入点处通过反射机制获取织入点处的方法
                sysLogService.insertSysLog(sysLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    ```
* 在文件和附件操作的控制器增加自定义注解实现日志记录
     ```java
        @ApiOperation(value = "删除附件", notes = "删除附件")
        @PostMapping(value = "/deleteAttachment")
        @ResponseBody
        @OperLog(operModul = "文档管理模块-附件管理子模块", operType = OperLogConstant.OPER_LOG_DELETE, operDesc = "删除附件")
        public ResultDTO<Integer> deleteAttachment(@RequestBody Integer attachmentId) {
            Integer isDelete = 1;
            return attachmentInfoService.deleteAttachment(attachmentId, isDelete);
    
        }
    
        @ApiOperation(value = "新增附件", notes = "新增附件")
        @PostMapping(value = "/addAttachment")
        @ResponseBody
        @OperLog(operModul = "文档管理模块-附件管理子模块", operType = OperLogConstant.OPER_LOG_ADD, operDesc = "新增附件")
        public ResultDTO<Integer> addAttachment(@ApiParam(required = true, name = "附件对象", value = "传入json格式数据") @RequestBody AttachmentInfo attachmentInfo) {
            return attachmentInfoService.addAttachment(attachmentInfo);
    
        }
    
        @ApiOperation(value = "附件查询", notes = "附件查询")
        @GetMapping(value = "/query/{fileId}")
        @ResponseBody
        public ResultDTO<List<AttachmentInfo>> queryAllAttachmentInfo(@ApiParam(required = true, name = "fileId", value = "文档id") @PathVariable("fileId") Integer fileId) {
            return attachmentInfoService.queryAllAttachmentInfo(fileId);
        }
  ...
    ```

### 消息管理平台
#### 1.数据库表设计
```sql
create table message_info
(
    id                     int auto_increment comment '消息编号'
        primary key,
    topic                  varchar(50)                        null comment '主题',
    content                text                               null comment '内容',
    send_user_id           int                                not null comment '发布者编号',
    accept_user_id         varchar(255)                       not null comment '接收者编号',
    send_time              datetime default CURRENT_TIMESTAMP null comment '发布时间',
    effective_time         datetime                           null comment '有效时间',
    is_save                int      default 0                 not null comment '是否保存到草稿箱 1是 0否',
    is_read                int      default 0                 not null comment '是否已读  1是 0否',
    is_delete_accept       int      default 0                 not null comment '是否被收件人删除  0 否 1是',
    is_delete_send         int      default 0                 not null comment '是否被发布人删除  0 否 1是',
    is_delete_accept_trash int      default 0                 not null comment '是否被收件人永久删除  0 否 1是',
    is_delete_send_trash   int      default 0                 not null comment '是否被发布人永久删除 0 否 1是',
    update_time            datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '消息表';
```

#### 2.接口设计
```java
/**
     * 保存邮箱
     *
     * @return
     */
    ResultDTO<Boolean> saveMail(MessageInfo messageInfo);

    /**
     * 收件箱查询
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<MessageInfo> quertAccessMail(MessageInfo messageInfo);

    /**
     * 发件箱查询
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<MessageInfo> querySendMail(MessageInfo messageInfo);

    /**
     * 草稿箱查询
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<MessageInfo> queryTempMail(MessageInfo messageInfo);

    /**
     * 垃圾箱查询
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<MessageInfo> queryDeleteMail(MessageInfo messageInfo);

    /**
     * 修改邮箱
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<Boolean> updateMail(MessageInfo messageInfo);

    /**
     * 删除邮箱
     *
     * @param emailId
     * @return
     */
    ResultDTO<Boolean> deleteMail(Integer emailId);
```
### 考勤管理系统
#### 1.数据库表设计
```sql
create table attends_date
(
    id              int auto_increment comment '日期编号'
        primary key,
    attend_year     varchar(10)   not null comment '年份',
    attend_month    varchar(10)   not null comment '月份',
    is_work         int default 0 null comment '是否工作日 0-工作日 1-假期',
    work_begin_time datetime      not null comment '上班时间',
    work_end_time   datetime      not null comment '下班时间',
    remark          text          null comment '备注',
    is_delete       int default 0 null comment '是否有效 0-有效 1-无效'
)
    comment '考勤日期管理';

create table attends_info
(
    id          int auto_increment comment '考勤编号'
        primary key,
    user_id     int                                not null comment '用户id',
    dept_id     int                                not null comment '部门id',
    remark      text                               null comment '备注',
    type        int                                null comment '考勤类型 0-上班打卡 1-下班打卡 ',
    attend_time datetime default CURRENT_TIMESTAMP null comment '考勤时间',
    status      int                                null comment '0-正常 1-迟到 2-早退 3-旷工'
)
    comment '考勤信息表';


```

#### 2.接口设计
##### 2.1 文档管理模块 
```java
 /**
     * 签到 签退
     *
     * @param attendsInfo
     * @return
     */
    ResultDTO<Boolean> signInAndSingOut(AttendsInfo attendsInfo);

    /**
     * 考勤历史查询
     * @param attendsInfo
     * @return
     */
    ResultDTO<List<AttendsInfo>> queryAttendList(AttendsInfo attendsInfo);

    /**
     * 考勤统计
     * @param attendsInfo
     * @return
     */
    ResultDTO<List<AttendsInfo>> attendStatistics(AttendsInfo attendsInfo);

    /**
     * 工作日设定
     * @param attendsDate
     * @return
     */
    ResultDTO<Boolean> insertWoryDay(AttendsDate attendsDate);

    /**
     * 工作日查询
     * @param attendsDate
     * @return
     */
    ResultDTO<List<Boolean>> queryWoryDay(AttendsDate attendsDate);
```

### 日程管理模块
#### 1.数据库表设计
```sql
create table schedule_info
(
    id          int auto_increment comment '编号'
        primary key,
    start_time  datetime                           not null comment '起始时间',
    end_time    datetime                           not null comment '结束时间',
    content     text                               not null comment '内容',
    address     varchar(100)                       not null comment '地点',
    remark      varchar(100)                       null comment '说明',
    is_show     int      default 1                 null comment '是否公开 0不公开 1公开',
    create_time datetime default CURRENT_TIMESTAMP null comment '日程创建时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '日程修改时间',
    is_delete   int      default 0                 null comment '是否删除 0不删除 1删除'
)
    comment '日程事件表';


```
#### 2.接口设计
##### 2.1 文档管理模块 
```java
/**
     * 新增日程
     *
     * @param scheduleInfo
     * @return
     */
    ResultDTO<Boolean> insertSchedule(ScheduleInfo scheduleInfo);


    /**
     * 查询日程信息
     *
     * @param scheduleInfo
     * @return
     */
    ResultDTO<List<ScheduleInfo>> querySchedule(ScheduleInfo scheduleInfo);

    /**
     * 修改日程信息
     *
     * @param scheduleInfo
     * @return
     */
    ResultDTO<List<ScheduleInfo>> updateSchedule(ScheduleInfo scheduleInfo);

```

### 系统管理系统
#### 1.数据库表设计
```sql
create table oa_system.user_info
(
    id                 int auto_increment comment '员工id'
        primary key,
    name               varchar(20)  not null comment '姓名',
    photo              varchar(100) not null comment '照片',
    age                int          not null comment '年龄',
    gender             int          not null comment '性别 0-女 1-男',
    address            varchar(255) not null comment '住址',
    email              varchar(100) not null comment '邮箱',
    mobile_phone       int          not null comment '移动电话',
    home_phone         int          not null comment '家庭电话',
    entry_time         datetime     not null comment '入职时间',
    office_location    varchar(255) not null comment '办公地点',
    office_phone       int          not null comment '办公电话',
    dept_id            int          not null comment '部门id',
    nation             varchar(20)  not null comment '民族',
    native_place       varchar(255) not null comment '籍贯',
    registered_address varchar(255) not null comment '户口所在地',
    birth              datetime     not null comment '出生年月',
    political_outlook  varchar(10)  not null comment '政治面貌',
    id_code            varchar(100) not null comment '身份证号码',
    marital_status     int          not null comment '婚姻状况 0-未婚 1-已婚',
    column_21          int          null,
    remarks            text         null comment '备注'
)
    comment '员工信息表';

create table oa_system.user_login_info
(
    id         int auto_increment comment '登录 ID'
        primary key,
    user_id    int          not null comment '用户编号',
    password   varchar(255) not null comment '用户密码',
    dept_id    int          not null comment '部门编号',
    role_id    int          not null comment '角色编号',
    ip_address varchar(100) not null comment '最近登录 IP ',
    status     int          null comment '登录状态',
    remarks    text         null comment '备注'
)
    comment '用户登录信息';


create table oa_system.sys_menu
(
    id        int auto_increment
        primary key,
    menu_icon varchar(255)  null comment '菜单图标',
    menu_name varchar(255)  null comment '菜单名称',
    menu_url  varchar(255)  null comment '菜单路径',
    parent_id int           null comment '父级菜单',
    sort_id   int           null comment '排序',
    is_show   int default 1 null comment '是否显示 1显示 0 隐藏',
    role_id   int           not null comment '角色编号'
)
    comment '菜单表';
create table oa_system.role_info
(
    id               int auto_increment comment '角色id'
        primary key,
    name             varchar(20)  not null comment '角色名称',
    permission_value int          not null comment '角色权限值',
    `desc`           varchar(100) not null comment '角色权限描述',
    remarks          text         null comment '备注'
)
    comment '角色表';

create table oa_system.dept_info
(
    id            int auto_increment comment '部门编号'
        primary key,
    lead_id       int         not null comment '部门经理编号',
    people_number int         not null comment '部门人数',
    name          varchar(20) not null comment '部门名称',
    create_time   datetime    not null comment '部门创建时间',
    remarks       text        null comment '备注'
)
    comment '部门信息';
```

#### 2.接口设计
##### 2.1 用户信息接口设计
```java
/**
     * 新增用户信息
     *
     * @param user_id
     * @return
     */
    ResultDTO<Boolean> addUserInfo(UserInfo user_id);

    /**
     * 删除用户
     *
     * @param user_id
     * @return
     */
    ResultDTO<Boolean> deleteUserInfo(Integer user_id);

    /**
     * 修改用户信息
     *
     * @param user_id
     * @return
     */
    ResultDTO<Boolean> upadteUserInfo(Integer user_id);
```
##### 2.2 角色信息接口设计
```java

    /**
     * 删除角色
     *
     * @param role_id
     * @return
     */
    ResultDTO<Boolean> deleteRole(Integer role_id);

    /**
     * 新增角色信息
     *
     * @param roleInfo
     * @return
     */
    ResultDTO<Boolean> addRole(RoleInfo roleInfo);

    /**
     * 修改角色信息
     *
     * @param role_id
     * @return
     */
    ResultDTO<Boolean> upadteRole(Integer role_id);

    /**
     * 查询所有角色
     *
     * @param roleInfo
     * @return
     */
    ResultDTO<List<RoleInfo>> queryRole(RoleInfo roleInfo);
```

##### 2.3 部门信息接口设计
```java
    /**
     * 删除部门
     *
     * @param dept_id
     * @return
     */
    ResultDTO<Boolean> deleteDept(Integer dept_id);

    /**
     * 新增部门
     *
     * @param deptInfo
     * @return
     */
    ResultDTO<Boolean> addDept(DeptInfo deptInfo);

    /**
     * 修改部门
     *
     * @param dept_id
     * @return
     */
    ResultDTO<Boolean> upadteDept(Integer dept_id);

    /**
     * 查询所有部门
     *
     * @param deptInfo
     * @return
     */
    ResultDTO<List<DeptInfo>> queryDept(DeptInfo deptInfo);
```


##### 2.4用户登录信息
```java
/**
     * 新增用户登录信息
     *
     * @param userLoginInfo
     * @return
     */
    ResultDTO<Boolean> addLoginInfo(UserLoginInfo userLoginInfo);
```
