package com.helloLottery.domain.activity.model.req;

import java.util.Date;

/**
 * @author liujun
 * @description: 活动参与入参
 * @date 2025/5/30 16:41
 */
public class PartakeReq {

    /**用户id*/
    private String uId;

    /**活动id*/
    private Long activityId;

    /**活动领取时间*/
    private Date partakeDate;

    public PartakeReq() {
    }

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }

    public PartakeReq(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
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

    public Date getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Date partakeDate) {
        this.partakeDate = partakeDate;
    }

}
