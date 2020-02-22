package com.oasystem.service.document.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.document.AttachmentInfoMapper;
import com.oasystem.dao.document.FileInfoMapper;
import com.oasystem.service.document.TrashService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName TrashServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/8 11:17 上午
 */
@Service
public class TrashServiceImpl implements TrashService {

    @Resource
    private AttachmentInfoMapper attachmentInfoMapper;
    @Resource
    private FileInfoMapper fileInfoMapper;

    @Override
    public ResultDTO<Integer> returnAttachment(Integer attachmentId, Integer isDelete) {
        int i = attachmentInfoMapper.updateById(attachmentId, isDelete);
        if (i > 0) {
            return ResultDTO.successResult(attachmentId);
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<Integer> returnFile(Integer fileId, Integer isDelete) {
        int i = fileInfoMapper.updateById(fileId, isDelete);
        if (i > 0) {
            return ResultDTO.successResult(fileId);
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<Integer> trashFile(Integer fileId) {
        int i = fileInfoMapper.deleteByPrimaryKey(fileId);
        if (i > 0) {
            return ResultDTO.successResult(fileId);
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<Integer> trashAttachment(Integer attachmentId) {
        int i = attachmentInfoMapper.deleteByPrimaryKey(attachmentId);
        if (i > 0) {
            return ResultDTO.successResult(attachmentId);
        }
        return ResultDTO.successResult(0);
    }
}
