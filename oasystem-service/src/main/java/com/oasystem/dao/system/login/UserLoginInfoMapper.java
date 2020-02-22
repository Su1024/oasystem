package com.oasystem.dao.system.login;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.oasystem.model.UserLoginInfo;

public interface UserLoginInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginInfo record);

    int insertSelective(UserLoginInfo record);

    UserLoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginInfo record);

    int updateByPrimaryKey(UserLoginInfo record);

    String chechUser(@Param("userCode") String userCode, @Param("password") String password);

    UserLoginInfo checkPwd(@Param("userCode") String userCode, @Param("password") String password);

    int updatePasswordByUserCode(@Param("updatedPassword")String updatedPassword,@Param("userCode")String userCode);



}