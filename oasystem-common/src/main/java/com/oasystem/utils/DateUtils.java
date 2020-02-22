package com.oasystem.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName DateUtils
 * @Description
 * @Author suguoming
 * @Date 2020/2/18 7:48 下午
 */
public class DateUtils {
    public static List<String> getMonthFullDay(int year, int month) {
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        List<String> fullDayList = new ArrayList<>(32);
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month - 1);
        // 当月1号
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 1; j <= count; j++) {
            fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return fullDayList;
    }

    public static String getHoliday(String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://timor.tech/api/holiday/info/"+date;
        return restTemplate.getForObject(url, String.class);


    }


    public static void main(String[] args) {

        List<String> monthFullDay = getMonthFullDay(2020, 1);
        String s = StringUtils.join(monthFullDay, ",").toString();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://timor.tech/api/holiday/batch?d={?}";
        String trans = restTemplate.getForObject(url, String.class, s);
        JSONObject jsonObject = JSONObject.parseObject(trans);
        Integer code = (Integer) jsonObject.get("code");
        if (code == 0) {
            JSONObject holiday = (JSONObject) jsonObject.get("holiday");
            monthFullDay.stream().filter(date -> {
                JSONObject holidayDate = (JSONObject)holiday.get(date);
                if (ObjectUtils.isEmpty(holidayDate)) {
                    return false;
                }
                if(!(boolean)holidayDate.get("holiday")){
                    return false;
                }
                return true;
            }).forEach(date -> {
                System.out.println(JSON.toJSONString(holiday.get(date)));

            });
        }
    }
}
