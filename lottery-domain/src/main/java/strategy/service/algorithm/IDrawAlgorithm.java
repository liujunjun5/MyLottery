package strategy.service.algorithm;

import strategy.model.vo.AwardRateInfo;

import java.util.List;

/**
 * @author: lj
 * 抽奖算法接口
 */
public interface IDrawAlgorithm {
    /**
     * 初始化概率元祖 使用过程中不允许修改
     *
     * @param strategyId
     * @param awardRateInfoList
     */
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);

    /**
     * 判断概率元组是否存在
     *
     * @param strategyId
     * @return
     */
    boolean isExistRateTuple(Long strategyId);

    /**
     * 生成随机数 索引到对应的奖品信息
     *
     * @param strategyId      使用的策略
     * @param excludeAwardIds 排除的奖品id
     * @return 中奖结果
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);
}
