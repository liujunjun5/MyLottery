package com.helloLottery.rpc.req;

import java.io.Serializable;

/**
 * @author lj
 */
public class ActivityReq implements Serializable {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

}
