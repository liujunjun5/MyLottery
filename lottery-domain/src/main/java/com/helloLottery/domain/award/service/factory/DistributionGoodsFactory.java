package com.helloLottery.domain.award.service.factory;

import com.helloLottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @version 1.0
 * @description: 配送奖品简单工厂
 * @date 2025/5/24 17:55
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{

    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }

}
