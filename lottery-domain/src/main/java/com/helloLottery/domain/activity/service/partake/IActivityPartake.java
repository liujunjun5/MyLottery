package com.helloLottery.domain.activity.service.partake;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;
import com.helloLottery.domain.activity.model.vo.DrawOrderVO;
import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 活动参与接口
 * @date 2025/5/25 15:42
 */
public interface IActivityPartake {
    /**
     * 参与活动
     * @param req 入参
     * @return    领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);
}
