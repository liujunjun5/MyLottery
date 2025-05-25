package com.helloLottery.domain.activity.service.stateflow;

import com.helloLottery.domain.activity.service.stateflow.event.*;
import com.hellolottery.common.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujun
 * @description: 初始化状态组
 * @date 2025/5/25 17:18
 */
public class StateConfig {

    @Resource
    private Arraignment arraignmentState;
    @Resource
    private Close closeState;
    @Resource
    private Doing doingState;
    @Resource
    private Editing editingState;
    @Resource
    private Opening openState;
    @Resource
    private Pass passState;
    @Resource
    private Refuse refuseState;

    protected Map<Enum<Constants.ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap();

    @PostConstruct
    public void init() {
        stateGroup.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        stateGroup.put(Constants.ActivityState.CLOSE, closeState);
        stateGroup.put(Constants.ActivityState.DOING, doingState);
        stateGroup.put(Constants.ActivityState.EDIT, editingState);
        stateGroup.put(Constants.ActivityState.OPEN, openState);
        stateGroup.put(Constants.ActivityState.PASS, passState);
        stateGroup.put(Constants.ActivityState.REFUSE, refuseState);
    }
}
