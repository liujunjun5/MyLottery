package com.helloLottery.domain.activity.service.deploy;

import com.helloLottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @author liujun
 * @description: 活动配置部署接口
 * @date 2025/5/25 15:14
 */
public interface IActivityDeploy {

    /***
     * @description: 活动创建
     * @param: req
     * @author liujun
     * @date: 2025/5/25 15:22
     */
    void createActivity(ActivityConfigReq req);

    /**
     * @description: 活动修改
     * @author liujun
     * @date: 2025/5/25 15:22
     */
    void updateActivity(ActivityConfigReq req);
}
