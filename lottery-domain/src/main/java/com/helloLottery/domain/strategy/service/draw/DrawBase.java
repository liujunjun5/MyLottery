package com.helloLottery.domain.strategy.service.draw;

import com.helloLottery.domain.strategy.model.vo.AwardRateInfo;
import com.helloLottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.helloLottery.interfaces.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lj
 * 初始化元组信息 同时将策略明细->奖品信息
 */
public class DrawBase extends DrawConfig {
    public void checkAndInitRateData(Long strategyId, List<StrategyDetail> strategyDetailList, Integer strategyMode) {
        // 不是使用元组初始化的
        if (strategyMode != 1) return;
        // 已经初始化完了
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if (existRateTuple) return;

        // 将 StrategyDetail 转换为 awardRateInfoList
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }
}
