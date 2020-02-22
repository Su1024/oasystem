package com.oasystem.service.system.dept;

import com.oasystem.ResultDTO;
import com.oasystem.model.DeptInfo;
import com.oasystem.model.dto.DeptQueryDTO;
import com.oasystem.model.dto.UserQueryDTO;

import java.util.List;

public interface DeptInfoService {

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    ResultDTO<Boolean> deleteDept(Integer deptId);

    /**
     * 新增部门
     *
     * @param deptInfo
     * @return
     */
    ResultDTO<Boolean> addDept(DeptInfo deptInfo);

    /**
     * 修改部门
     *
     * @param deptInfo
     * @return
     */
    ResultDTO<Boolean> upadteDept(DeptInfo deptInfo);

    /**
     * 分页查询部门信息
     *
     * @param page
     * @param limit
     * @param deptQueryDTO
     * @return
     */
    List<DeptInfo> queryList(Integer page, Integer limit, DeptQueryDTO deptQueryDTO);

    /**
     * 根据主键查询部门信息
     *
     * @param id
     * @return
     */
    ResultDTO<DeptInfo> getDeptById(Integer id);

    /**
     * 查询所有部门信息
     * @return
     */
    ResultDTO<List<DeptInfo>> getDeptList();
}
