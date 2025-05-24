package com.helloLottery.domain.activity.model.req;

import com.helloLottery.domain.activity.model.aggregates.ActivityConfigRich;

/**
 * @author liujun
 * @version 1.0
 * @description: 活动配置请求对象
 * @date 2025/5/24 21:33
 */
public class ActivityConfigReq {

    /** 活动id **/
    private Long activityId;

    /** 活动配置信息 **/
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }
}
