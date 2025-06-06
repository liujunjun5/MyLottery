package com.helloLottery.test;

import com.alibaba.fastjson.JSON;
import com.helloLottery.application.process.IActivityProcess;
import com.helloLottery.application.process.req.DrawProcessReq;
import com.helloLottery.application.process.res.DrawProcessResult;
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
 * @date 2025/6/6 12:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {
    private Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq("fustack", 100001L));
        logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
    }
}
