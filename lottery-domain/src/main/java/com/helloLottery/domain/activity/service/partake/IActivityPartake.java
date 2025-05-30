package com.helloLottery.domain.activity.service.partake;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;

/**
 * @author liujun
 * @description: 活动参与接口
 * @date 2025/5/25 15:42
 */
public interface IActivityPartake {

    PartakeResult doPartake(PartakeReq req);

}
