package com.helloLottery.domain.support.ids.policy;

import com.helloLottery.domain.support.ids.IIdGenerator;

import java.util.Calendar;
import java.util.Random;

/**
 * @author liujun
 * @description: 拼接年月日随机数字生成编号，适用于数据量不大的活动id
 * @date 2025/5/27 9:47
 */
public class ShortCode implements IIdGenerator {
    @Override
    public long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder idStr = new StringBuilder();
        idStr.append(year - 2025);
        idStr.append(hour);
        idStr.append(String.format("%02d", week));
        idStr.append(day);
        idStr.append(String.format("%03d", new Random().nextInt(1000)));
        return Long.parseLong(idStr.toString());
    }
}
