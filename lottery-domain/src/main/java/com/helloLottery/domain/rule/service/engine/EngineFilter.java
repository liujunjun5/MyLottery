package com.helloLottery.domain.rule.service.engine;

import com.helloLottery.domain.rule.model.req.DecisionMatterReq;
import com.helloLottery.domain.rule.model.res.EngineResult;

/**
 * @author liujun
 * @description:
 * @date 2025/6/7 21:37
 */
public interface EngineFilter {

    /**
     * 规则过滤器接口
     *
     * @param matter      规则决策物料
     * @return            规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);

}
