package com.oasystem.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oasystem.ResultDTO;
import com.oasystem.model.DeptInfo;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.DeptQueryDTO;
import com.oasystem.model.dto.UserQueryDTO;
import com.oasystem.service.system.dept.DeptInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptController
 * @Description
 * @Author suguoming
 * @Date 2020/2/16 8:50 下午
 */
@Api(tags = "部门管理模块")
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptInfoService deptInfoService;

    @ApiOperation(value = "部门查询", notes = "分页查询部门信息")
    @PostMapping(value = "/queryList/{page}/{limit}")
    @ResponseBody
    public ResultDTO<JSONObject> queryList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @RequestBody DeptQueryDTO deptQueryDTO) {
        //创建JSONmap来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        PageHelper.startPage(page, limit);
        List<DeptInfo> deptInfoList = deptInfoService.queryList(page, limit, deptQueryDTO);
        PageInfo<DeptInfo> pageInfo = new PageInfo<>(deptInfoList);
        map.put("count", pageInfo.getTotal());
        map.put("data", deptInfoList);
        return ResultDTO.successResult(map);
    }


    @ApiOperation(value = "查询所有部门", notes = "查询所有部门")
    @GetMapping(value = "/getDeptList")
    @ResponseBody
    public ResultDTO<List<DeptInfo>> getDeptList() {
        return deptInfoService.getDeptList();
    }


    @PostMapping(value = "/addUser")
    @ApiOperation(value = "新增部门", notes = "新增部门")
    @ResponseBody
    public ResultDTO<Boolean> addUser(@ApiParam(required = true, name = "部门对象", value = "传入json格式数据") @RequestBody DeptInfo deptInfo) {
        return deptInfoService.addDept(deptInfo);
    }


    @PostMapping(value = "/updateUser")
    @ApiOperation(value = "修改部门", notes = "修改部门")
    @ResponseBody
    public ResultDTO<Boolean> updateUser(@ApiParam(required = true, name = "部门对象", value = "传入json格式数据") @RequestBody DeptInfo deptInfo) {
        return deptInfoService.upadteDept(deptInfo);
    }

    @ApiOperation(value = "部门id查询", notes = "部门id查询")
    @GetMapping(value = "/getUserById/{id}")
    @ResponseBody
    public ResultDTO<DeptInfo> getDeptById(@PathVariable("id") Integer id) {
        return deptInfoService.getDeptById(id);
    }

    @ApiOperation(value = "部门删除", notes = "部门删除")
    @GetMapping(value = "/deleteById/{id}")
    @ResponseBody
    public ResultDTO<Boolean> deleteById(@PathVariable("id") Integer id) {
        return deptInfoService.deleteDept(id);
    }

}
