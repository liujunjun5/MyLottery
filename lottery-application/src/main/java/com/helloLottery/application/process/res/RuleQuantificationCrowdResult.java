package com.helloLottery.application.process.res;

import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description:
 * @date 2025/6/8 20:12
 */
public class RuleQuantificationCrowdResult extends Result {

    /** 活动ID */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
