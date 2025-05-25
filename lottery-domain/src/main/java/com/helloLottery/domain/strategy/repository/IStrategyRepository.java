package com.helloLottery.domain.strategy.repository;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.model.vo.DrawAwardInfo;

import java.util.List;
/**
 * @description 策略领域的仓储服务
 * @author lj
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    DrawAwardInfo queryAwardInfo(String awardId);

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
