package com.oasystem.service.attend;

import com.oasystem.ResultDTO;
import com.oasystem.model.AttendsDate;
import com.oasystem.model.AttendsInfo;
import com.oasystem.model.dto.AttendQueryDTO;
import com.oasystem.model.dto.WorkDate;
import com.oasystem.model.dto.WorkTime;
import com.oasystem.model.vo.AttendVo;

import java.util.List;

/**
 * @ClassName AttendsService
 * @Description
 * @Author suguoming
 * @Date 2020/2/11 2:50 下午
 */
public interface AttendsService {

    /**
     * 签到 签退
     *
     * @param attendsInfo
     * @return
     */
    ResultDTO<Boolean> signInAndSingOut(AttendsInfo attendsInfo);

    /**
     * 考勤历史查询
     * @param attendsInfo
     * @return
     */
    ResultDTO<List<AttendsInfo>> queryAttendList(AttendsInfo attendsInfo);

    /**
     * 考勤统计
     * @param attendsInfo
     * @return
     */
    ResultDTO<List<AttendsInfo>> attendStatistics(AttendsInfo attendsInfo);

    /**
     * 工作日设定
     * @param attendsDate
     * @return
     */
    void insertWoryDay(AttendsDate attendsDate);

    /**
     * 工作日查询
     * @param attendsDate
     * @return
     */
    ResultDTO<List<Boolean>> queryWoryDay(AttendsDate attendsDate);


    /**
     * 考勤历史查询
     * @param page
     * @param limit
     * @param attendQueryDTO
     * @return
     */
    List<AttendsInfo> queryList(Integer page, Integer limit, AttendQueryDTO attendQueryDTO);

    /**
     * 考勤统计
     * @param page
     * @param limit
     * @param attendQueryDTO
     * @return
     */
    List<AttendVo> queryCountList(Integer page, Integer limit, AttendQueryDTO attendQueryDTO);

    /**
     * 下载
     * @param attendQueryDTO
     * @return
     */
    List<AttendVo> queryCountList(AttendQueryDTO attendQueryDTO);

    /**
     * 查询所有假期
     * @return
     */
    ResultDTO<List<String>> queryAllholiday();

    /**
     * 工作日设定
     * @param workDate
     * @return
     */
    ResultDTO<Boolean> setWorkDay(WorkDate workDate);

    /**
     * 工作时间设定
     * @param workTime
     * @return
     */
    ResultDTO<Boolean> setWorkTime(WorkTime workTime);

    /**
     * 工作时间查询
     * @return
     */
    ResultDTO<WorkTime> getWorkTime();
}
