package com.helloLottery.domain.strategy.service.algorithm.impl;

import com.helloLottery.domain.strategy.model.vo.AwardRateVO;
import com.helloLottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lj
 * 必定会中奖 排除掉已经中奖的概率 重新计算中奖范围
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        //        String
        // 统计当前的总概率
        BigDecimal current = BigDecimal.ZERO;
        // 记录当前的剩余奖品概率
        List<AwardRateVO> currentAwateList = new ArrayList<>();
        // 获取完整的概率
        List<AwardRateVO> awardRateVOList = super.awardRateInfoMap.get(strategyId);
        // 减去需要过滤的奖品id 统计总概率和当前奖品id
        for (AwardRateVO awardRateVO : awardRateVOList) {
            // 在排除集合中
            String awardId = awardRateVO.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            current = current.add(awardRateVO.getAwardRate());
            currentAwateList.add(awardRateVO);
        }
        // 无奖品或者只有一种奖品
        if (currentAwateList.size() == 0) return "";
        if (currentAwateList.size() == 1) return currentAwateList.get(0).getAwardId();
        // 进行抽奖 1.随机数
        String awardId = "";
        int randomVal = new SecureRandom().nextInt(100) + 1;
        // 2.计算所在区间
        int cursor = 0;
        for (AwardRateVO awardRateVO : currentAwateList) {
            int curRate = awardRateVO.getAwardRate().divide(current, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (curRate + cursor >= randomVal) {
                awardId = awardRateVO.getAwardId();
                break;
            }
            cursor += curRate;
        }
        // 返回结果
        return awardId;
    }
}
