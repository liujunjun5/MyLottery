package com.helloLottery.test;

import com.helloLottery.domain.support.ids.IIdGenerator;
import com.helloLottery.domain.support.ids.IdContext;
import com.helloLottery.domain.support.ids.policy.RandomNumeric;
import com.helloLottery.domain.support.ids.policy.ShortCode;
import com.helloLottery.domain.support.ids.policy.SnowFlake;
import com.hellolottery.common.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liujun
 * @description:
 * @date 2025/5/27 10:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdGeneratorTest {

    private Logger logger = LoggerFactory.getLogger(IdGeneratorTest.class);

    @Resource
    private SnowFlake snowFlake;
    @Resource
    private ShortCode shortCode;
    @Resource
    private RandomNumeric randomNumeric;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Test
    public void test_ids() {
        System.out.println(snowFlake.nextId());
        System.out.println(shortCode.nextId());
        System.out.println(randomNumeric.nextId());
    }

    @Test
    public void test_ids1() {
        logger.info("雪花算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        logger.info("日期算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.ShortCode).nextId());
        logger.info("随机算法策略，生成ID：{}", idGeneratorMap.get(Constants.Ids.RandomNumeric).nextId());
    }
}
