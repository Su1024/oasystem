package com.oasystem.service.system.login.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.system.login.UserLoginInfoMapper;
import com.oasystem.model.UserLoginInfo;
import com.oasystem.model.dto.LoginInfo;
import com.oasystem.service.system.login.LoginSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName LoginSerivceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 1:08 上午
 */
@Service
public class LoginSerivceImpl implements LoginSerivce {

    @Resource
    private UserLoginInfoMapper userLoginInfoMapper;

    @Override
    public ResultDTO<Boolean> updatePwd(LoginInfo loginInfo) {
        int i = userLoginInfoMapper.updatePasswordByUserCode(loginInfo.getPassword(), loginInfo.getUserId());
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.errorResult("修改失败！");
    }

    @Override
    public ResultDTO<Boolean> checkPwd(LoginInfo loginInfo) {
        UserLoginInfo userLoginInfo = userLoginInfoMapper.checkPwd(loginInfo.getUserId(), loginInfo.getPassword());
        if (userLoginInfo != null) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.errorResult("校验失败！");

    }

    @Override
    public ResultDTO<Boolean> addLoginInfo(UserLoginInfo userLoginInfo) {
        return null;
    }

    @Override
    public String chechUser(LoginInfo loginInfo) {
        return userLoginInfoMapper.chechUser(loginInfo.getUsername(), loginInfo.getPassword());
    }
}
