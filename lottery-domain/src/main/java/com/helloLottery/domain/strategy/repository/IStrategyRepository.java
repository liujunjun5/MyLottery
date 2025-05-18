package com.helloLottery.domain.strategy.repository;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.infrastructure.po.Award;
import java.util.List;
/**
 * @author lj
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);

    /**
     * 查询策略中没有库存的奖品列表
     * @param strategyId 策略id
     * @return 奖品id列表
     */
    List<String> queryNoStackStrategyAwareList(Long strategyId);

    /**
     * 扣除当前策略的奖品库存
     * @param strategyId 策略id
     * @param awardId 奖品id
     * @return 扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);
}
