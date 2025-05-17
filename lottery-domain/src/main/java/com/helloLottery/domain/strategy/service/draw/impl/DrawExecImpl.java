package com.helloLottery.domain.strategy.service.draw.impl;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.repository.IStrategyRepository;
import com.helloLottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.helloLottery.domain.strategy.service.draw.DrawBase;
import com.helloLottery.interfaces.po.Award;
import com.helloLottery.interfaces.po.Strategy;
import com.helloLottery.interfaces.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.helloLottery.domain.strategy.model.req.DrawReq;
import com.helloLottery.domain.strategy.model.res.DrawResult;
import com.helloLottery.domain.strategy.service.draw.IDrawExec;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        /**
         * 基本流程：
         * 1.获取策略信息聚合
         * 1.1 需要策略、策略明细 1-n关系
         * 1.2 做基本的校验 是否需要 or 已经创建元组
         * 2.抽奖
         * 2.1 执行抽奖
         * 2.2 包装结果返回
         */
        logger.info("执行策略抽奖开始， strategyId:{}", req.getStrategyId());

        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();

        checkAndInitRateData(strategy.getStrategyId(), strategyDetailList, strategy.getStrategyMode());

        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = algorithm.randomDraw(strategy.getStrategyId(), new ArrayList<>());
        Award award = strategyRepository.queryAwardInfo(awardId);
        logger.info("抽奖结果如下，用户：{}，奖品id：{}，奖品名称：{}", req.getuId(), awardId, award.getAwardName());
        return new DrawResult(req.getuId(), strategy.getStrategyId(), awardId, award.getAwardName());
    }
}
