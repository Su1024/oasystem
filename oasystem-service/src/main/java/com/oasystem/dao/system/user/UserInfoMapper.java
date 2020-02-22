package com.oasystem.dao.system.user;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.UserQueryDTO;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectAll(UserQueryDTO userQueryDTO);

    UserInfo selectByUserCode(@Param("userCode")String userCode);




}