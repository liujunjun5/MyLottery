package com.helloLottery.infrastructure.po;

import java.util.Date;

/**
 * @author liujun
 * @description: 用户活动参与表
 * @date 2025/5/29 19:41
 */
public class UserTakeActivity {

    /**自增id*/
    private Long id;

    /**用户id*/
    private String uId;

    /**参与id*/
    private Long takeId;

    /**活动id*/
    private Long activityId;

    /**活动名*/
    private String activityName;

    /**参与时间*/
    private Date takeDate;

    /**参与次数*/
    private Integer takeCount;

    /**防重复*/
    private String uuid;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

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

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
