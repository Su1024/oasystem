package com.oasystem.service.document.impl;

import com.alibaba.fastjson.JSON;
import com.oasystem.ResultDTO;
import com.oasystem.dao.document.FileInfoMapper;
import com.oasystem.model.FileInfo;
import com.oasystem.model.dto.FileQueryDTO;
import com.oasystem.model.vo.FileInfoVo;
import com.oasystem.service.document.FileInfoService;
import com.oasystem.utils.QiniuUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName FileInfoServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/7 3:10 下午
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private QiniuUtils qiniuUtils;

    @Override
    public ResultDTO<Integer> moveFile(Integer id, Integer pid) {
        int i = fileInfoMapper.moveFile(id, pid);
        if (i > 0) {
            return ResultDTO.successResult(i);
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public Map upload(MultipartFile file) {
        try {
            String upload = qiniuUtils.upload(file);
            return JSON.parseObject(upload, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultDTO<List<FileInfoVo>> getAllFolders(FileQueryDTO fileQueryDTO) {
        List<FileInfo> fileInfos = fileInfoMapper.getAllFolders(fileQueryDTO);
        if (fileInfos != null) {
            List<FileInfoVo> collect = fileInfos.stream().map(data -> {
                FileInfoVo fileInfoVo = new FileInfoVo();
                BeanUtils.copyProperties(data, fileInfoVo);
                return fileInfoVo;

            }).collect(Collectors.toList());
            return ResultDTO.successResult(collect);
        }
        return ResultDTO.successResult(Collections.emptyList());
    }

    @Override
    public ResultDTO<List<FileInfoVo>> queryFile(FileQueryDTO fileQueryDTO) {
        List<FileInfo> fileInfos = fileInfoMapper.queryFile(fileQueryDTO);
        if (fileInfos != null) {
            List<FileInfoVo> collect = fileInfos.stream().map(data -> {
                FileInfoVo fileInfoVo = new FileInfoVo();
                BeanUtils.copyProperties(data, fileInfoVo);
                return fileInfoVo;

            }).collect(Collectors.toList());
            return ResultDTO.successResult(collect);
        }
        return ResultDTO.successResult(Collections.emptyList());
    }

    @Override
    public ResultDTO<Integer> deleteFile(List<Integer> fileIds, Integer isDelete) {
        int res = 0;
        for (Integer fileId : fileIds) {
            int insert = fileInfoMapper.updateById(fileId, isDelete);
            if (insert > 0) {
                res = insert;
            }
        }
        return ResultDTO.successResult(res);
    }

    @Override
    public ResultDTO<Integer> updateFile(FileInfo fileInfo) {
        int insert = fileInfoMapper.updateByPrimaryKeySelective(fileInfo);
        if (insert > 0) {
            return ResultDTO.successResult(fileInfo.getId());
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public ResultDTO<Integer> addFile(FileInfo fileInfo) {
        int insert = fileInfoMapper.insertSelective(fileInfo);
        if (insert > 0) {
            return ResultDTO.successResult(fileInfo.getId());
        }
        return ResultDTO.successResult(0);
    }

    @Override
    public List<FileInfo> queryAll(Integer groupId) {
        return fileInfoMapper.selectAllByGroupId(groupId);
    }
}
