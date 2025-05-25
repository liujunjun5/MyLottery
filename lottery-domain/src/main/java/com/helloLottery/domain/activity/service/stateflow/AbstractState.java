package com.helloLottery.domain.activity.service.stateflow;

import com.helloLottery.domain.activity.repository.IActivityRepository;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;

import javax.annotation.Resource;

/**
 * @author liujun
 * @description: 抽象状态定义
 * @date 2025/5/25 16:10
 */
public abstract class AbstractState {

    @Resource
    protected IActivityRepository activityRepository;

    /*** 
     * @description: 提审活动 
     * @param: activityId 活动id currentState 当前状态
     * @return: com.hellolottery.common.Result 
     * @author liujun
     * @date: 2025/5/25 16:17
     */ 
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /*** 
     * @description: 通过活动 
     * @param: activityId 活动id currentState 
     * @return: com.hellolottery.common.Result 
     * @author liujun
     * @date: 2025/5/25 16:20
     */ 
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /***
     * @description 拒绝活动
     * @author liujun
     * @date 16:36 2025/5/25
     * @param activityId
     * @param currentState
     * @return com.hellolottery.common.Result
     **/
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * @description 撤审活动
     * @author liujun
     * @date 16:37 2025/5/25
     * @param activityId
     * @param currentState
     * @return com.hellolottery.common.Result
     **/
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /***
     * @description 开启活动
     * @author liujun
     * @date 16:37 2025/5/25
     * @param activityId
     * @param currentState
     * @return com.hellolottery.common.Result
     **/
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentState);

    /***
     * @description 活动进行中
     * @author liujun
     * @date 16:38 2025/5/25
     * @param activityId
     * @param currentState
     * @return com.hellolottery.common.Result
     **/
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentState);

    /***
     * @description 活动关闭
     * @author liujun
     * @date 16:38 2025/5/25
     * @param activityId
     * @param currentState
     * @return com.hellolottery.common.Result
     **/
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentState);
    
}
