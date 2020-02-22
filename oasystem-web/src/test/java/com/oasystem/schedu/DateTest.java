package com.oasystem.schedu;

import com.alibaba.fastjson.JSONObject;
import com.oasystem.model.AttendsDate;
import com.oasystem.service.attend.AttendsService;
import com.oasystem.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName DateTest
 * @Description
 * @Author suguoming
 * @Date 2020/2/18 9:44 下午
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    @Resource
    private AttendsService attendsService;

    @Test
    public void test(){
        Integer sysYear = getSysYear();
        for (int i = 1; i <= 12; i++) {
            List<String> monthFullDay = DateUtils.getMonthFullDay(sysYear, i);
            monthFullDay.forEach(date -> {
                String trans = DateUtils.getHoliday(date);
                JSONObject jsonObject = JSONObject.parseObject(trans);
                Integer code = (Integer) jsonObject.get("code");
                if (code == 0) {
                    JSONObject type = (JSONObject) jsonObject.get("type");
                    Integer typeNum = (Integer) type.get("type");
                    boolean isWork = false;
                    if (typeNum == 0 || typeNum == 3) {
                        isWork = true;
                    }
                    AttendsDate attendsDate = new AttendsDate();
                    attendsDate.setAttendYear(String.valueOf(sysYear));
                    attendsDate.setAttendDate(date);
                    attendsDate.setIsWork(isWork ? 1 : 0);
                    attendsService.insertWoryDay(attendsDate);
                }
            });

        }
    }
    public static Integer getSysYear() {
        Calendar date = Calendar.getInstance();
        return Integer.valueOf(date.get(Calendar.YEAR));
    }
}
