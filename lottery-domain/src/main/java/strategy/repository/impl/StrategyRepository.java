package strategy.repository.impl;

import com.helloLottery.infrastructure.dao.IAwardDao;
import com.helloLottery.infrastructure.dao.IStrategyDao;
import com.helloLottery.infrastructure.dao.IStrategyDetailDao;
import com.helloLottery.infrastructure.po.Award;
import com.helloLottery.infrastructure.po.Strategy;
import com.helloLottery.infrastructure.po.StrategyDetail;
import strategy.model.aggregates.StrategyRich;
import strategy.repository.IStrategyRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lj
 */
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetails);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }
}
