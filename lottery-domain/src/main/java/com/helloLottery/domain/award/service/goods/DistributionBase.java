package com.helloLottery.domain.award.service.goods;

import com.helloLottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardState(String uid, String orderId, String awardId, Integer awardState, String awardStateInfo) {
        logger.info("更新用户中奖奖品发货状态uid:{}", uid);
        // TODO
    }

}
