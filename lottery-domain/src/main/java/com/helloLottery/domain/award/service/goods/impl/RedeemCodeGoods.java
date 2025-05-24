package com.helloLottery.domain.award.service.goods.impl;

import com.hellolottery.common.Constants;
import com.helloLottery.domain.award.model.req.GoodsReq;
import com.helloLottery.domain.award.model.res.DistributionRes;
import com.helloLottery.domain.award.service.goods.DistributionBase;
import com.helloLottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @version 1.0
 * @description: TODO
 * @date 2025/5/24 17:42
 */
@Component
public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用兑换码
        logger.info("模拟调用兑换码 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

}
