package com.oasystem.dao.document;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.oasystem.model.AttachmentInfo;

public interface AttachmentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttachmentInfo record);

    int insertSelective(AttachmentInfo record);

    AttachmentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttachmentInfo record);

    int updateByPrimaryKey(AttachmentInfo record);

    List<AttachmentInfo> selectAllByFileIdOrderByCreateTimeDesc(@Param("fileId") Integer fileId);

    int updateById(@Param("id") Integer id, @Param("isDelete") Integer isDelete);


}