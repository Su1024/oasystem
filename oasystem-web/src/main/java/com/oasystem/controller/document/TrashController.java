package com.oasystem.controller.document;

import com.oasystem.ResultDTO;
import com.oasystem.annotation.OperLog;
import com.oasystem.constant.OperLogConstant;
import com.oasystem.service.document.TrashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName TrashController
 * @Description
 * @Author suguoming
 * @Date 2020/2/8 11:14 上午
 */
@Api(tags = "回收站管理")
@Controller
@RequestMapping("/trash")
public class TrashController {
    @Resource
    private TrashService trashService;

    @ApiOperation(value = "还原附件", notes = "还原附件")
    @PostMapping(value = "/returnAttachment")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-回收站子模块", operType = OperLogConstant.OPER_LOG_UPDATE, operDesc = "还原附件")
    public ResultDTO<Integer> returnAttachment(@ApiParam(required = true, name = "attachmentId", value = "附件Id") @RequestBody Integer attachmentId) {
        Integer isDelete = 0;
        return trashService.returnAttachment(attachmentId, isDelete);

    }

    @ApiOperation(value = "还原文档", notes = "还原文档")
    @PostMapping(value = "/returnFile")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-回收站子模块", operType = OperLogConstant.OPER_LOG_UPDATE, operDesc = "还原文档")
    public ResultDTO<Integer> returnFile(@RequestBody Integer fileId) {
        Integer isDelete = 0;
        return trashService.returnFile(fileId, isDelete);

    }

    @ApiOperation(value = "彻底删除附件", notes = "彻底删除附件")
    @PostMapping(value = "/trashAttachment")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-回收站子模块", operType = OperLogConstant.OPER_LOG_DELETE, operDesc = "彻底删除附件")
    public ResultDTO<Integer> trashAttachment(@RequestBody Integer attachmentId) {
        return trashService.trashAttachment(attachmentId);

    }

    @ApiOperation(value = "彻底删除文档", notes = "彻底删除文档")
    @PostMapping(value = "/trashFile")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-回收站子模块", operType = OperLogConstant.OPER_LOG_DELETE, operDesc = "彻底删除文档")
    public ResultDTO<Integer> trashFile(@RequestBody Integer fileId) {
        return trashService.trashFile(fileId);

    }

}
