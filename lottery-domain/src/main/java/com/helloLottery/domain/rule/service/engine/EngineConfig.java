package com.helloLottery.domain.rule.service.engine;

import com.helloLottery.domain.rule.service.logic.LogicFilter;
import com.helloLottery.domain.rule.service.logic.impl.UserAgeFilter;
import com.helloLottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujun
 * @description:
 * @date 2025/6/7 21:38
 */
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }

}
