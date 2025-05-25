package com.helloLottery.domain.activity.service.stateflow.event;

import com.helloLottery.domain.activity.service.stateflow.AbstractState;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @description: 编辑状态
 * @date 2025/5/25 16:39
 */
@Component
public class Editing extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.ARRAIGNMENT);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动提审成功") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("编辑中不可变更活动通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("编辑中不可变更活动拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("编辑中不可变更活动撤审");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("编辑中不可变更活动开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("编辑中不可变更活动进行中");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE );
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动关闭成功") : Result.buildErrorResult("活动状态变更失败");
    }
}
