package com.helloLottery.domain.award.service.factory;

import com.helloLottery.domain.award.service.goods.IDistributionGoods;
import com.helloLottery.domain.award.service.goods.impl.CouponGoods;
import com.helloLottery.domain.award.service.goods.impl.DescGoods;
import com.helloLottery.domain.award.service.goods.impl.PhysicalGoods;
import com.helloLottery.domain.award.service.goods.impl.RedeemCodeGoods;
import com.hellolottery.common.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujun
 * @version 1.0
 * @description: 初始化发奖工厂
 * @date 2025/5/24 17:48
 */
public class GoodsConfig {

    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
