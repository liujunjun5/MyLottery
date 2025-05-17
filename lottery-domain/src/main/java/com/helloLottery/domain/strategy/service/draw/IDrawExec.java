package com.helloLottery.domain.strategy.service.draw;

import com.helloLottery.domain.strategy.model.req.DrawReq;
import com.helloLottery.domain.strategy.model.res.DrawResult;

public interface IDrawExec {
    // 执行抽奖策略
    DrawResult doDrawExec(DrawReq req);
}
