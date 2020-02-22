package com.oasystem.controller.attend;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oasystem.ResultDTO;
import com.oasystem.model.AttendsInfo;
import com.oasystem.model.dto.AttendQueryDTO;
import com.oasystem.model.dto.WorkDate;
import com.oasystem.model.dto.WorkTime;
import com.oasystem.model.vo.AttendVo;
import com.oasystem.service.attend.AttendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @ClassName AttendController
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 6:35 下午
 */
@Api(tags = "考勤管理模块")
@Controller
@RequestMapping("/attend")
public class AttendController {
    @Resource
    private AttendsService attendsService;

    @ApiOperation(value = "考勤历史查询", notes = "分页考勤历史查询")
    @PostMapping(value = "/queryList/{page}/{limit}")
    @ResponseBody
    public ResultDTO<JSONObject> queryList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @RequestBody AttendQueryDTO attendQueryDTO) {
        //创建JSONmap来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        PageHelper.startPage(page, limit);
        List<AttendsInfo> attendsInfos = attendsService.queryList(page, limit, attendQueryDTO);
        PageInfo<AttendsInfo> pageInfo = new PageInfo<>(attendsInfos);
        map.put("count", pageInfo.getTotal());
        map.put("data", attendsInfos);
        return ResultDTO.successResult(map);
    }

    @ApiOperation(value = "考勤统计查询", notes = "分页考勤统计查询")
    @PostMapping(value = "/queryCountList/{page}/{limit}")
    @ResponseBody
    public ResultDTO<JSONObject> queryCountList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @RequestBody AttendQueryDTO attendQueryDTO) {
        //创建JSONmap来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        PageHelper.startPage(page, limit);
        List<AttendVo> attendsInfos = attendsService.queryCountList(page, limit, attendQueryDTO);
        PageInfo<AttendVo> pageInfo = new PageInfo<>(attendsInfos);
        map.put("count", pageInfo.getTotal());
        map.put("data", attendsInfos);
        return ResultDTO.successResult(map);
    }

    @ApiOperation(value = "下载考勤统计", notes = "下载考勤统计")
    @PostMapping(value = "/downLoad")
    @ResponseBody
    public void downLoad(HttpServletResponse response, @RequestBody AttendQueryDTO attendQueryDTO) throws IOException {
        List<AttendVo> attendsInfos = attendsService.queryCountList(attendQueryDTO);
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);

        // 新增数据行，并且设置单元格数据
        int rowNum = 1;

        // headers表示excel表中第一行的表头 在excel表中添加表头
        String[] headers = {"员工编号", "员工姓名", "所属部门", "迟到次数", "早退次数", "旷工次数", "考勤日期"};
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (AttendVo item : attendsInfos) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(item.getUserCode());
            row1.createCell(1).setCellValue(item.getName());
            row1.createCell(2).setCellValue(item.getDeptId());
            row1.createCell(3).setCellValue(item.getLate());
            row1.createCell(4).setCellValue(item.getEarly());
            row1.createCell(5).setCellValue(item.getAbsenteeism());
            row1.createCell(6).setCellValue(item.getAttendTime());
            rowNum++;
        }

        //浏览器下载excel
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=salesRefundList.xls");
        response.flushBuffer();
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    @ApiOperation(value = "查询所有假期", notes = "查询所有假期")
    @GetMapping(value = "/queryAllholiday")
    @ResponseBody
    public ResultDTO<List<String>> queryAllholiday() {
        return attendsService.queryAllholiday();
    }

    @ApiOperation(value = "设置工作日", notes = "设置工作日")
    @PostMapping(value = "/setWorkDay")
    @ResponseBody
    public ResultDTO<Boolean> setWorkDay(@RequestBody WorkDate workDate) {
        return attendsService.setWorkDay(workDate);
    }


    @ApiOperation(value = "设置工作时间", notes = "设置工作时间")
    @PostMapping(value = "/setWorkTime")
    @ResponseBody
    public ResultDTO<Boolean> setWorkTime(@RequestBody WorkTime workTime) {
        return attendsService.setWorkTime(workTime);
    }

    @ApiOperation(value = "获取工作时间", notes = "获取工作时间")
    @GetMapping(value = "/getWorkTime")
    @ResponseBody
    public ResultDTO<WorkTime> getWorkTime() {
        return attendsService.getWorkTime();
    }


}
