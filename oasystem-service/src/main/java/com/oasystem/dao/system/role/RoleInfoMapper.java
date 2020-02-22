package com.oasystem.dao.system.role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.oasystem.model.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

}