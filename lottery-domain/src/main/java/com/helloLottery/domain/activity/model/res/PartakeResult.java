package com.helloLottery.domain.activity.model.res;

import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 活动参与结果
 * @date 2025/5/30 16:43
 */
public class PartakeResult extends Result {

    /**
     * 策略ID
     */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
