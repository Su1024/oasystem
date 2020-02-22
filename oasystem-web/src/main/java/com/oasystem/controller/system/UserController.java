package com.oasystem.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oasystem.ResultDTO;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.UserQueryDTO;
import com.oasystem.service.system.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author suguoming
 * @Date 2020/2/15 11:07 下午
 */
@Api(tags = "用户管理模块")
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @Value("${uploadFilePath}")
    private String filePath;

    @ApiOperation(value = "用户查询", notes = "分页查询用户信息")
    @PostMapping(value = "/queryList/{page}/{limit}")
    @ResponseBody
    public ResultDTO<JSONObject> queryList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @RequestBody UserQueryDTO userQueryDTO) {
        //创建JSONmap来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        PageHelper.startPage(page, limit);
        List<UserInfo> userInfoList = userInfoService.queryList(page, limit, userQueryDTO);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        map.put("count", pageInfo.getTotal());
        map.put("data", userInfoList);
        return ResultDTO.successResult(map);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }

    @PostMapping(value = "/addUser")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ResponseBody
    public ResultDTO<Boolean> addUser(@ApiParam(required = true, name = "用户对象", value = "传入json格式数据") @RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }


    @PostMapping(value = "/updateUser")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ResponseBody
    public ResultDTO<Boolean> updateUser(@ApiParam(required = true, name = "用户对象", value = "传入json格式数据") @RequestBody UserInfo userInfo) {
        return userInfoService.upadteUserInfo(userInfo);
    }

    @ApiOperation(value = "用户id查询", notes = "用户id查询")
    @GetMapping(value = "/getUserById/{id}")
    @ResponseBody
    public ResultDTO<UserInfo> getUserById(@PathVariable("id") Integer id) {
        return userInfoService.getUserById(id);
    }

    @ApiOperation(value = "用户删除", notes = "用户删除")
    @GetMapping(value = "/deleteById/{id}")
    @ResponseBody
    public ResultDTO<Boolean> deleteById(@PathVariable("id") Integer id) {
        return userInfoService.deleteyById(id);
    }


}
