package com.helloLottery.domain.activity.service.stateflow;

import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 活动状态变更接口
 * @date 2025/5/25 17:17
 */
public interface IStateHandle {
    /**
     * 提审
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 审核通过
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 审核拒绝
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 撤销审核
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 关闭
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result close(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 开启
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result open(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     * 运行活动中
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return              审核结果
     */
    Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus);
}
