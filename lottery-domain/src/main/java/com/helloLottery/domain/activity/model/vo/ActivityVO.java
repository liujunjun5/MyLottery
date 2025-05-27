package com.helloLottery.domain.activity.model.vo;

import java.util.Date;

/**
 * @author liujun
 * @version 1.0
 * @description: 活动信息配置
 * @date 2025/5/24 21:39
 */
public class ActivityVO {

    /**活动id**/
    private Long activityId;

    /**活动名称**/
    private String activityName;

    /**活动描述**/
    private String activityDesc;

    /**开始时间**/
    private Date beginDateTime;

    /**结束时间**/
    private Date endDateTime;

    /**库存**/
    private Integer stockCount;

    /**每人可参与次数**/
    private Integer takeCount;

    /**活动状态（编辑、提审、撤审、通过、工作、拒绝、关闭）*/
    private Integer state;

    /**创建者**/
    private String creator;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "ActivityVO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityDesc='" + activityDesc + '\'' +
                ", beginDateTime=" + beginDateTime +
                ", endDateTime=" + endDateTime +
                ", stockCount=" + stockCount +
                ", takeCount=" + takeCount +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                '}';
    }
}
