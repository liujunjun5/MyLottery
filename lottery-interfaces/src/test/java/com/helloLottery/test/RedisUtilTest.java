package com.helloLottery.test;

import com.alibaba.fastjson.JSON;
import com.helloLottery.interfaces.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liujun
 * @description:
 * @date 2025/6/10 22:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    private Logger logger = LoggerFactory.getLogger(RedisUtilTest.class);

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void test_set() {
        redisUtil.set("lottery_user_key", "liujun");
    }

    @Test
    public void test_get() {
        Object user = redisUtil.get("lottery_user_key");
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

}

