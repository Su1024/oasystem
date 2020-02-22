package com.oasystem.service.system.user;

import com.oasystem.ResultDTO;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.UserQueryDTO;

import java.util.List;

/**
 * @ClassName UserInfoService
 * @Description
 * @Author suguoming
 * @Date 2020/2/11 4:08 下午
 */
public interface UserInfoService {


    /**
     * 删除用户
     *
     * @param user_id
     * @return
     */
    ResultDTO<Boolean> deleteUserInfo(Integer user_id);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    ResultDTO<Boolean> upadteUserInfo(UserInfo userInfo);

    List<UserInfo> queryList(Integer page, Integer limit, UserQueryDTO userQueryDTO);

    /**
     * 新增用户信息
     *
     * @param userInfo
     * @return
     */
    ResultDTO<Boolean> addUser(UserInfo userInfo);

    ResultDTO<UserInfo> getUserById(Integer id);

    ResultDTO<Boolean> deleteyById(Integer id);


    UserInfo selectByUserCode(String userCode);

}
