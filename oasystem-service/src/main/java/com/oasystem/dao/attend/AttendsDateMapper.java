package com.oasystem.dao.attend;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.oasystem.model.AttendsDate;

public interface AttendsDateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttendsDate record);

    int insertSelective(AttendsDate record);

    AttendsDate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttendsDate record);

    int updateByPrimaryKey(AttendsDate record);


    List<String> selectAttendDateByIsWork();

    int updateIsWorkByAttendDate(@Param("isWork")Integer isWork,@Param("currentDate")String currentDate);


    int updateWorkTime(@Param("beginTime")String beginTime,@Param("endTime")String endTime);


    AttendsDate selectWorkTime();

}