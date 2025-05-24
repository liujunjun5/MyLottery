package com.helloLottery.domain.award.service.goods.impl;

import com.helloLottery.domain.award.model.req.GoodsReq;
import com.helloLottery.domain.award.model.res.DistributionRes;
import com.helloLottery.domain.award.service.goods.DistributionBase;
import com.helloLottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;
import com.hellolottery.common.Constants;

/**
 * @author lj
 * 描述类奖品
 */
@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }
}
