package com.helloLottery.domain.rule.service.logic.impl;

import com.helloLottery.domain.rule.model.req.DecisionMatterReq;
import com.helloLottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @description: 年龄规则
 * @date 2025/6/7 21:33
 */
@Component
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }

}
