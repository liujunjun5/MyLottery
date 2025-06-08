package com.helloLottery.application.process;

import com.helloLottery.application.process.req.DrawProcessReq;
import com.helloLottery.application.process.res.DrawProcessResult;
import com.helloLottery.application.process.res.RuleQuantificationCrowdResult;
import com.helloLottery.domain.rule.model.req.DecisionMatterReq;

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

    /**
     * 规则量化人群，返回可参与的活动ID
     * @param req   规则请求
     * @return      量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);
}
