package com.oasystem.controller;

import com.oasystem.ResultDTO;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.LoginInfo;
import com.oasystem.service.system.login.LoginSerivce;
import com.oasystem.service.system.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 12:44 上午
 */
@Api(tags = "登录模块")
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginSerivce loginSerivce;
    @Resource
    private UserInfoService userInfoService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "登录")
    @ResponseBody
    public ResultDTO<UserInfo> login(@RequestBody LoginInfo loginInfo, HttpSession session) {
        String userCode = loginSerivce.chechUser(loginInfo);
        UserInfo userInfo = userInfoService.selectByUserCode(userCode);
        return ResultDTO.successResult(userInfo);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出", notes = "退出")
    @ResponseBody
    public ResultDTO<UserInfo> logout(@RequestBody UserInfo userInfo) {
        return ResultDTO.successResult(userInfo);
    }


    @PostMapping(value = "/pwd")
    @ApiOperation(value = "校验密码", notes = "校验密码")
    @ResponseBody
    public ResultDTO<Boolean> pwd(@RequestBody LoginInfo loginInfo) {
        return loginSerivce.checkPwd(loginInfo);
    }

    @PutMapping(value = "/pwd")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ResponseBody
    public ResultDTO<Boolean> updatePwd(@RequestBody LoginInfo loginInfo) {
        return loginSerivce.updatePwd(loginInfo);
    }
}
