package com.oasystem.service.system.dept.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.system.dept.DeptInfoMapper;
import com.oasystem.model.DeptInfo;
import com.oasystem.model.dto.DeptQueryDTO;
import com.oasystem.service.system.dept.DeptInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptInfoServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/16 8:54 下午
 */
@Service
public class DeptInfoServiceImpl implements DeptInfoService {

    @Resource
    private DeptInfoMapper deptInfoMapper;

    @Override
    public ResultDTO<Boolean> deleteDept(Integer deptId) {
        int i = deptInfoMapper.deleteByPrimaryKey(deptId);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> addDept(DeptInfo deptInfo) {
        int i = deptInfoMapper.insertSelective(deptInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> upadteDept(DeptInfo deptInfo) {
        int i = deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }


    @Override
    public List<DeptInfo> queryList(Integer page, Integer limit, DeptQueryDTO deptQueryDTO) {
        return deptInfoMapper.selectAllPage(deptQueryDTO);
    }


    @Override
    public ResultDTO<DeptInfo> getDeptById(Integer id) {
        return ResultDTO.successResult(deptInfoMapper.selectByPrimaryKey(id));
    }

    @Override
    public ResultDTO<List<DeptInfo>> getDeptList() {
        return ResultDTO.successResult(deptInfoMapper.selectAllPage(null));
    }
}
