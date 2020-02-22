package com.oasystem.service.document.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.document.AttachmentInfoMapper;
import com.oasystem.model.AttachmentInfo;
import com.oasystem.service.document.AttachmentInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName AttachmentInfoServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/7 2:55 下午
 */
@Service
public class AttachmentInfoServiceImpl implements AttachmentInfoService {

    @Resource
    private AttachmentInfoMapper attachmentInfoMapper;

    @Override
    public ResultDTO<Integer> deleteAttachment(Integer attachmentId, Integer isDelete) {
        int i = attachmentInfoMapper.updateById(attachmentId, isDelete);
        if (i > 0) {
            return ResultDTO.successResult(attachmentId);
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<Integer> addAttachment(AttachmentInfo attachmentInfo) {
        int i = attachmentInfoMapper.insertSelective(attachmentInfo);
        if (i > 0) {
            return ResultDTO.successResult(attachmentInfo.getId());
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<List<AttachmentInfo>> queryAllAttachmentInfo(Integer fileId) {
        List<AttachmentInfo> attachmentInfos = attachmentInfoMapper.selectAllByFileIdOrderByCreateTimeDesc(fileId);
        if (!CollectionUtils.isEmpty(attachmentInfos)) {
            return ResultDTO.successResult(attachmentInfos);
        }
        return ResultDTO.successResult(Collections.EMPTY_LIST);
    }
}

