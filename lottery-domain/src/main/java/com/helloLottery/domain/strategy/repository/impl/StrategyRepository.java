package com.helloLottery.domain.strategy.repository.impl;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.repository.IStrategyRepository;
import com.helloLottery.interfaces.dao.IAwardDao;
import com.helloLottery.interfaces.dao.IStrategyDao;
import com.helloLottery.interfaces.dao.IStrategyDetailDao;
import com.helloLottery.interfaces.po.Award;
import com.helloLottery.interfaces.po.Strategy;
import com.helloLottery.interfaces.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lj
 */
@Component
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
