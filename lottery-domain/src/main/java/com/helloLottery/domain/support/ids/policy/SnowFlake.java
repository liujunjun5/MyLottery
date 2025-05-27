package com.helloLottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.helloLottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liujun
 * @description: 利用雪花算法返回id
 * @date 2025/5/27 10:14
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    /***
     * @description
     * 算法算法 64位，第一位固定为0表示正数，41位时间戳，5位数据中心id，5位机器号，12位随机数
     * @author liujun
     * @date 10:30 2025/5/27
     **/
    @PostConstruct
    public void init() {
        long workId, dataCenterId = 1L;
        // 传入一个5位的workId
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workId = NetUtil.getLocalhostStr().hashCode();
        }
        workId = workId >> 16 & 31;
        snowflake = IdUtil.createSnowflake(workId, dataCenterId);
    }

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}
