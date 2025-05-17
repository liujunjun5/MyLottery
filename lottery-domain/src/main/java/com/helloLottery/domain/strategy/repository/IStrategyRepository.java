package com.helloLottery.domain.strategy.repository;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.interfaces.po.Award;

/**
 * @author lj
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}
