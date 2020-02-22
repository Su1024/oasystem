package com.oasystem.dao.system.dept;
import com.oasystem.model.dto.DeptQueryDTO;
import org.apache.ibatis.annotations.Param;

import com.oasystem.model.DeptInfo;

import java.util.List;

public interface DeptInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeptInfo record);

    int insertSelective(DeptInfo record);

    DeptInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeptInfo record);

//    List<DeptInfo>
    List<DeptInfo> selectAllPage(DeptQueryDTO deptQueryDTO);

}