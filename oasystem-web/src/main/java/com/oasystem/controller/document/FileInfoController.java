package com.oasystem.controller.document;

import com.oasystem.ResultDTO;
import com.oasystem.annotation.OperLog;
import com.oasystem.constant.OperLogConstant;
import com.oasystem.model.AttachmentInfo;
import com.oasystem.model.FileInfo;
import com.oasystem.model.dto.FileQueryDTO;
import com.oasystem.model.vo.FileInfoVo;
import com.oasystem.service.document.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FileInfoController
 * @Description
 * @Author suguoming
 * @Date 2020/2/7 3:12 下午
 */
@Api(tags = "文档管理模块")
@Controller
@RequestMapping("/document")
public class FileInfoController {

    @Resource
    private FileInfoService fileInfoService;

    @GetMapping(value = "/moveFile/{id}/{pid}")
    @ApiOperation(value = "移动文档", notes = "移动文档")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-文档管理子模块", operType = OperLogConstant.OPER_LOG_DELETE, operDesc = "移动文档")
    public ResultDTO<Integer> moveFile(@ApiParam(required = true, name = "fileId", value = "文档id") @PathVariable("id") Integer id,@PathVariable("id") Integer pid) {
        Integer isDelete = 1;
        return fileInfoService.moveFile(id, pid);
    }

    @GetMapping(value = "/deleteFile/{fileIds}")
    @ApiOperation(value = "删除文档", notes = "删除文档")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-文档管理子模块", operType = OperLogConstant.OPER_LOG_DELETE, operDesc = "删除文档")
    public ResultDTO<Integer> deleteFile(@ApiParam(required = true, name = "fileId", value = "文档id") @PathVariable("fileIds") List<Integer> fileIds) {
        Integer isDelete = 1;
        return fileInfoService.deleteFile(fileIds, isDelete);
    }

    @PostMapping(value = "/updateFile")
    @ApiOperation(value = "修改文档", notes = "修改文档")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-文档管理子模块", operType = OperLogConstant.OPER_LOG_UPDATE, operDesc = "修改文档")

    public ResultDTO<Integer> updateFile(@ApiParam(required = true, name = "文档对象", value = "传入json格式数据") @RequestBody FileInfo fileInfo) {
        return fileInfoService.updateFile(fileInfo);
    }

    @PostMapping(value = "/addFile")
    @ApiOperation(value = "新增文档", notes = "新增文档")
    @ResponseBody
    @OperLog(operModul = "文档管理模块-文档管理子模块", operType = OperLogConstant.OPER_LOG_ADD, operDesc = "新增文档")
    public ResultDTO<Integer> addFile(@ApiParam(required = true, name = "文档对象", value = "传入json格式数据") @RequestBody FileInfo fileInfo) {
        return fileInfoService.addFile(fileInfo);
    }



    @ApiOperation(value = "文档查询", notes = "查询该部门下的所有文档")
    @PostMapping(value = "/queryFile/{deptId}")
    @ResponseBody
    public ResultDTO<List<FileInfoVo>> query(@PathVariable("deptId") Integer deptId, @RequestBody FileQueryDTO fileQueryDTO) {
        fileQueryDTO.setGroupId(deptId);
        return fileInfoService.queryFile(fileQueryDTO);
    }

    @ApiOperation(value = "文档夹查询", notes = "查询该部门下的所有文档")
    @GetMapping(value = "/getAllFolders/{deptId}")
    @ResponseBody
    public ResultDTO<List<FileInfoVo>> getAllFolders(@PathVariable("deptId") Integer deptId) {
        FileQueryDTO fileQueryDTO = new FileQueryDTO();
        fileQueryDTO.setGroupId(deptId);
        return fileInfoService.getAllFolders(fileQueryDTO);
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResultDTO<Map> upload(@RequestParam("file") MultipartFile file) {
        Map upload = fileInfoService.upload(file);
        if (upload != null) {
            return ResultDTO.successResult(upload);
        }
        return ResultDTO.errorResult("上传失败！");
    }



}

