package com.oasystem.controller.document;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oasystem.ResultDTO;
import com.oasystem.model.SysLog;
import com.oasystem.model.dto.FileQueryDTO;
import com.oasystem.service.sysLog.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OperateLog
 * @Description
 * @Author suguoming
 * @Date 2020/2/15 8:57 下午
 */
@Api(tags = "操作日志")
@Controller
@RequestMapping("/operateLog")
public class OperateLogController {

    @Resource
    private SysLogService sysLogService;

    @ApiOperation(value = "文档日志管理", notes = "查询文档的操作日志记录")
    @GetMapping(value = "/query")
    @ResponseBody
    public ResultDTO<List<SysLog>> query() {
        return sysLogService.querySysLog();
    }

    @ApiOperation(value = "文档日志管理", notes = "分页查询文档的操作日志记录")
    @PostMapping(value = "/queryList/{page}/{limit}")
    @ResponseBody
    public ResultDTO<JSONObject> queryList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,@RequestBody FileQueryDTO searchMap) {
        //创建JSONmap来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        PageHelper.startPage(page, limit);
        List<SysLog> sysLogs = sysLogService.queryList(page, limit);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        map.put("count", pageInfo.getTotal());
        map.put("data", sysLogs);
        return ResultDTO.successResult(map);
    }
}
