package com.helloLottery.test;

import com.alibaba.fastjson.JSON;
import com.helloLottery.domain.rule.model.req.DecisionMatterReq;
import com.helloLottery.domain.rule.model.res.EngineResult;
import com.helloLottery.domain.rule.service.engine.EngineFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author liujun
 * @description:
 * @date 2025/6/8 12:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ruleTest {
    private Logger logger = LoggerFactory.getLogger(ruleTest.class);
    @Resource
    private EngineFilter engineFilter;

    @Test
    public void test_process() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("lj");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = engineFilter.process(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
