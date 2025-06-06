package com.helloLottery.domain.strategy.service.algorithm;

import com.helloLottery.domain.strategy.model.vo.AwardRateInfo;
import com.hellolottery.common.Constants;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lj
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {
    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    // 均匀覆盖无周期性、互质与2^m
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放奖品与概率的映射
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    // 存放策略与奖品的映射关系
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateInfo> awardRateInfoList) {

        // 前置判断
        if (isExist(strategyId)){
            return;
        }

        // 保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateInfoList);

        // 非单项概率，不必存入缓存，因为这部分抽奖算法需要实时处理中奖概率。
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : awardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();

            // 循环填充概率范围值
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }

            cursorVal += rateVal;

        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    /**
     * 斐波那契散列法计算哈希索引下标 互质特性避免扎堆 均匀分布性扩容友好 ThreadLocal运用
     */
    protected int hashIdx(int val) {
        int hash = val * HASH_INCREMENT + HASH_INCREMENT;
        return hash & (RATE_TUPLE_LENGTH - 1);
    }

    @Override
    public boolean isExist(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }
}
