package com.helloLottery.test;

import com.helloLottery.domain.support.ids.policy.RandomNumeric;
import com.helloLottery.domain.support.ids.policy.ShortCode;
import com.helloLottery.domain.support.ids.policy.SnowFlake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liujun
 * @description:
 * @date 2025/5/27 10:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdGeneratorTest {
    @Resource
    private SnowFlake snowFlake;
    @Resource
    private ShortCode shortCode;
    @Resource
    private RandomNumeric randomNumeric;

    @Test
    public void test_ids() {
        System.out.println(snowFlake.nextId());
        System.out.println(shortCode.nextId());
        System.out.println(randomNumeric.nextId());
    }
}
