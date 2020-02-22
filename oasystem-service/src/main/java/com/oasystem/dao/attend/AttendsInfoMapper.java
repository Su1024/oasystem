package com.oasystem.dao.attend;
import com.oasystem.model.dto.AttendQueryDTO;
import com.oasystem.model.vo.AttendVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.oasystem.model.AttendsInfo;

public interface AttendsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttendsInfo record);

    int insertSelective(AttendsInfo record);

    AttendsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttendsInfo record);

    int updateByPrimaryKey(AttendsInfo record);

    List<AttendsInfo> selectAllByDeptIdAndAttendTime(AttendQueryDTO attendQueryDTO);

    List<AttendVo> selectCountAllByDeptIdAndAttendTime(AttendQueryDTO attendQueryDTO);


}