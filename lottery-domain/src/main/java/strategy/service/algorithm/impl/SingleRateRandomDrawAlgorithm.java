package strategy.service.algorithm.impl;

import org.springframework.stereotype.Component;
import strategy.service.algorithm.BaseAlgorithm;

import java.security.SecureRandom;
import java.util.List;

/**
 * @description: 抽到一个已经排掉的奖品则未中奖
 * @author: lj
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] rateTupe = super.rateTupleMap.get(strategyId);
        assert rateTupe != null;

        int randomVal = new SecureRandom().nextInt(100) + 1;
        String awardId = rateTupe[hashIdx(randomVal)];

        if (excludeAwardIds.contains(awardId)) {
            return "未中奖";
        }
        return awardId;
    }
}
