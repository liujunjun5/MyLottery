package com.helloLottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import com.helloLottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.helloLottery.domain.strategy.service.draw.AbstractDrawBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抽奖的实现类
 *
 * @author lj
 */
@Service("drawExec")
public class DrawExecImpl extends AbstractDrawBase {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> excludeAwardIds = strategyRepository.queryNoStackStrategyAwareList(strategyId);
        logger.info("执行抽奖策略 strategyId：{}，无库存排除奖品列表ID集合 awardList：{}", strategyId, JSON.toJSONString(excludeAwardIds));
        return excludeAwardIds;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        if (awardId == null) {
            return null;
        }
        /**
         * 目前使用了数据库表 行锁的方式扣减库存 后续优化为redis分布式锁的方式
         * 数据库表锁行锁不能处理大体量的并发
         */
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        return isSuccess ? awardId : null;
    }

//    @Override
//    public DrawResult doDrawExec(DrawReq req) {
//        /**
//         * 基本流程：
//         * 1.获取策略信息聚合
//         * 1.1 需要策略、策略明细 1-n关系
//         * 1.2 做基本的校验 是否需要 or 已经创建元组
//         * 2.抽奖
//         * 2.1 执行抽奖
//         * 2.2 包装结果返回
//         */
//        logger.info("执行策略抽奖开始， strategyId:{}", req.getStrategyId());
//
//        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
//        Strategy strategy = strategyRich.getStrategy();
//        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();
//
//        checkAndInitRateData(strategy.getStrategyId(), strategyDetailList, strategy.getStrategyMode());
//
//        IDrawAlgorithm algorithm = drawAlgorithmGroup.get(strategy.getStrategyMode());
//        String awardId = algorithm.randomDraw(strategy.getStrategyId(), new ArrayList<>());
//        Award award = strategyRepository.queryAwardInfo(awardId);
//        logger.info("抽奖结果如下，用户：{}，奖品id：{}，奖品名称：{}", req.getuId(), awardId, award.getAwardName());
//        return new DrawResult(req.getuId(), strategy.getStrategyId(), awardId, award.getAwardName());
//    }


}
