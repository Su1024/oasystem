package com.oasystem.dao.document;

import java.util.Date;

import com.oasystem.model.dto.FileQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.oasystem.model.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);


    List<FileInfo> selectAllByGroupId(@Param("groupId") Integer groupId);

    int updateById(@Param("id") Integer id, @Param("isDelete") Integer isDelete);


    List<FileInfo> queryFile(FileQueryDTO fileQueryDTO);


    List<FileInfo> getAllFolders(FileQueryDTO fileQueryDTO);

    int moveFile(@Param("id") Integer id, @Param("pid") Integer pid);

}