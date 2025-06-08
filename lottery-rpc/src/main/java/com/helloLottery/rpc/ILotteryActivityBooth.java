package com.helloLottery.rpc;

import com.helloLottery.rpc.req.DrawReq;
import com.helloLottery.rpc.req.QuantificationDrawReq;
import com.helloLottery.rpc.res.DrawRes;

/**
 * @author liujun
 * @description: 抽奖活动接口
 * @date 2025/6/8 20:25
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq 请求参数
     * @return        抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);



}
