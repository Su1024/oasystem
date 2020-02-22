package com.oasystem.controller.document;

import com.oasystem.ResultDTO;
import com.oasystem.annotation.OperLog;
import com.oasystem.constant.OperLogConstant;
import com.oasystem.constant.OperLogEnum;
import com.oasystem.model.AttachmentInfo;
import com.oasystem.service.document.AttachmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AttachmentController
 * @Description
 * @Author suguoming
 * @Date 2020/2/8 12:42 上午
 */
@Api(tags = "附件管理模块")
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Resource
    private AttachmentInfoService attachmentInfoService;

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
}
