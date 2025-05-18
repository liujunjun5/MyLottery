package com.helloLottery.domain.strategy.service.draw;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.repository.IStrategyRepository;
import com.helloLottery.infrastructure.po.Award;

import javax.annotation.Resource;

/**
 * @author lj
 * 抽奖策略的数据支撑 提供通用的数据服务
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     *
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected Award queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }

}
