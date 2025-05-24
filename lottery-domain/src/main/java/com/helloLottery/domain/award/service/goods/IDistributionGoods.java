package com.helloLottery.domain.award.service.goods;

import com.helloLottery.domain.award.model.req.GoodsReq;
import com.helloLottery.domain.award.model.res.DistributionRes;

public interface IDistributionGoods {
    /**
     * 商品配送
     * @param req 物品信息（用户id、奖品信息、地址值对象、单号）
     * @return 配送结果
     */
    DistributionRes doDistribution(GoodsReq req);

}
