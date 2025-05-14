package strategy.repository;

import com.helloLottery.infrastructure.po.Award;
import strategy.model.aggregates.StrategyRich;

/**
 * @author lj
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}
