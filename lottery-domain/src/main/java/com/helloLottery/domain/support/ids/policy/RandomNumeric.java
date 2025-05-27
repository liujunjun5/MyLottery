package com.helloLottery.domain.support.ids.policy;

import com.helloLottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @description: 工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @date 2025/5/25 22:50
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
