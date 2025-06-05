package com.helloLottery.domain.activity.service.partake;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;
import com.helloLottery.domain.activity.model.vo.ActivityBillVO;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 用户参与活动模板类
 * @date 2025/6/5 9:58
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Override
    public PartakeResult doPartake(PartakeReq req) {
        // 获取活动相关信息
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 活动信息校验
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 活动库存的扣减
        Result subStockResult = this.subActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subStockResult.getCode())) {
            return new PartakeResult(subStockResult.getCode(), subStockResult.getInfo());
        }

        // 用户参与活动
        Result takeActivityResult = this.takeActivity(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(takeActivityResult.getCode())) {
            return new PartakeResult(takeActivityResult.getCode(), takeActivityResult.getInfo());
        }

        // 封装并且返回结果
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(activityBillVO.getStrategyId());
        return partakeResult;
    }

    /***
     * @description 基础信息校验
     * @author liujun
     * @date 10:54 2025/6/5
     * @param req 参与Id
     * @param activityBillVO 活动相关信息
     * @return com.hellolottery.common.Result
     **/
    protected abstract Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO);

    /**
     * @description: 扣减活动次数
     * @author liujun
     * @date 2025/6/5 10:54
     */
    protected abstract Result subActivityStock(PartakeReq req);

    /**
     * @description: 用户参与活动
     * @author liujun
     * @date 2025/6/5 10:55
     */
    protected abstract Result takeActivity(PartakeReq req, ActivityBillVO activityBillVO);
}
