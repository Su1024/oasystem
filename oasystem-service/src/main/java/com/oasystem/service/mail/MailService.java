package com.oasystem.service.mail;

import com.oasystem.ResultDTO;
import com.oasystem.model.MessageInfo;

/**
 * @ClassName MailService
 * @Description
 * @Author suguoming
 * @Date 2020/2/10 4:51 下午
 */
public interface MailService {

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


}
