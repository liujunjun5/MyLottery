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
 * @description: 优惠券商品
 * @date 2025/5/24 11:20
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        // 调用优惠券发放接口
        logger.info("调用优惠券发放接口 uid：{} awardContent：{}",req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.FAILURE.getInfo());
    }
}
