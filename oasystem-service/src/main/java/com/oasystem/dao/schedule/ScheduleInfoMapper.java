package com.oasystem.dao.schedule;

import com.oasystem.model.ScheduleInfo;

public interface ScheduleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleInfo record);

    int insertSelective(ScheduleInfo record);

    ScheduleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleInfo record);

    int updateByPrimaryKey(ScheduleInfo record);
}