package com.helloLottery.domain.strategy.model.aggregates;

import com.helloLottery.domain.activity.model.vo.StrategyDetailVO;
import com.helloLottery.domain.activity.model.vo.StrategyVO;
import com.helloLottery.domain.strategy.model.vo.StrategyDetailBriefVO;


import java.util.List;

public class StrategyRich {
    private Long strategyId;
    private StrategyVO strategy;
    private List<StrategyDetailBriefVO> strategyDetailList;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyVO strategy, List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyVO strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}
