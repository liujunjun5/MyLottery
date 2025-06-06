package com.helloLottery.application.process;

import com.helloLottery.application.process.req.DrawProcessReq;
import com.helloLottery.application.process.res.DrawProcessResult;

/**
 * @author liujun
 * @description:
 * @date 2025/6/6 10:21
 */
public interface IActivityProcess {

    /**
     * @description: 执行抽奖流程
     * @author liujun
     * @date 2025/6/6 10:26
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
