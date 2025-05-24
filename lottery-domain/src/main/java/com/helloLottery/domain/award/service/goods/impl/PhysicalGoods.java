package com.helloLottery.domain.award.service.goods.impl;

import com.helloLottery.domain.award.model.req.GoodsReq;
import com.helloLottery.domain.award.model.res.DistributionRes;
import com.helloLottery.domain.award.service.goods.DistributionBase;
import com.helloLottery.domain.award.service.goods.IDistributionGoods;
import com.hellolottery.common.Constants;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @version 1.0
 * @description: 物理奖品
 * @date 2025/5/24 11:20
 */
@Component
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 调用实物发奖
        logger.info("调用实物发奖 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

}
