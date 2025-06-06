package com.helloLottery.application.process.req;

/**
 * @author liujun
 * @description: 抽奖请求
 * @date 2025/6/6 10:24
 */
public class DrawProcessReq {

    private Long activityId;

    private String uId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
