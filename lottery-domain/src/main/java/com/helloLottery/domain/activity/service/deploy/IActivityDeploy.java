package com.helloLottery.domain.activity.service.deploy;

import com.helloLottery.domain.activity.model.req.ActivityConfigReq;
import com.helloLottery.domain.activity.model.vo.ActivityVO;

import java.util.List;

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

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     * <p>
     * 通过 -> 时间符合时 -> 活动中
     * 活动中 -> 时间到期时 -> 关闭
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<ActivityVO> scanToDoActivityList(Long id);
}
