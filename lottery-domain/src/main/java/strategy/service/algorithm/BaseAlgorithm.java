package strategy.service.algorithm;

import strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lj
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {
    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    // 是一个无理数 均匀覆盖无周期性
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放奖品与概率的映射
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    // 存放策略与奖品的映射关系
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {
        // 存放策略和奖品映射 例如 strategyId 1 --> List<AwardRateInfo> {"一等奖：IMac", new BigDecimal("0.05"),"二等奖：iphone", new BigDecimal("0.15"),"三等奖：ipad", new BigDecimal("0.20")}
        awardRateInfoMap.put(strategyId, awardRateInfoList);

        // 初始化元组
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k->new String[RATE_TUPLE_LENGTH]);

        int cursor = 0;
        for (AwardRateInfo awardRateInfo : awardRateInfoList) {
            int rate = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
            for (int i = cursor + 1; i <= (rate + cursor); i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }
            cursor += rate;
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
}
