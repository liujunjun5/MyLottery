package strategy.service.algorithm;

import java.util.List;

public interface IDrawAlgorithm {
    /**
     * 作为所有抽奖策略的入口
     * @param strategyId 使用的策略
     * @param excludeAwardIds 排除的奖品id
     * @return 中奖结果
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);
}
