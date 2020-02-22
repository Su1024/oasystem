package com.oasystem.service.system.user.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.system.dept.DeptInfoMapper;
import com.oasystem.dao.system.user.UserInfoMapper;
import com.oasystem.model.DeptInfo;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.UserQueryDTO;
import com.oasystem.service.system.user.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName UserInfoServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/15 11:15 下午
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    @Resource
    private DeptInfoMapper deptInfoMapper;

    @Override
    public ResultDTO<Boolean> addUser(UserInfo userInfo) {
        int id = userInfoMapper.insertSelective(userInfo);
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setLeadId(id);
        deptInfo.setId(userInfo.getDeptId());
        deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        if (id > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> deleteUserInfo(Integer user_id) {
        return null;
    }

    @Override
    public ResultDTO<Boolean> upadteUserInfo(UserInfo userInfo) {
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setLeadId(userInfo.getId());
        deptInfo.setId(userInfo.getDeptId());
        deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public List<UserInfo> queryList(Integer page, Integer limit, UserQueryDTO userQueryDTO) {

        List<UserInfo> userInfoList = userInfoMapper.selectAll(userQueryDTO);
        if (CollectionUtils.isEmpty(userInfoList)) {
            return Collections.EMPTY_LIST;
        }
        return userInfoList;
    }

    @Override
    public ResultDTO<UserInfo> getUserById(Integer id) {
        return ResultDTO.successResult(userInfoMapper.selectByPrimaryKey(id));
    }

    @Override
    public ResultDTO<Boolean> deleteyById(Integer id) {
        int i = userInfoMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public UserInfo selectByUserCode(String userCode) {
        return userInfoMapper.selectByUserCode(userCode);
    }
}
