package com.helloLottery.test;

import com.hellolottery.common.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.helloLottery.domain.strategy.model.vo.AwardRateVO;
import com.helloLottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest // 指定主类
public class DrawAlgorithmTest {
        @Resource(name = "defaultRateRandomDrawAlgorithm")
//    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Before
    public void init() {
        // 奖品信息
        List<AwardRateVO> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateVO("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateVO("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateVO("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateVO("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateVO("五等奖：充电宝", new BigDecimal("0.35")));

        // 初始数据
        randomDrawAlgorithm.initRateTuple(100001L, Constants.StrategyMode.SINGLE.getCode(), strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {

        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
        }

    }
}
