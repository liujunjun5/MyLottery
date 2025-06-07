package com.helloLottery.domain.rule.service.engine.impl;

import com.helloLottery.domain.rule.model.aggregates.TreeRuleRich;
import com.helloLottery.domain.rule.model.req.DecisionMatterReq;
import com.helloLottery.domain.rule.model.res.EngineResult;
import com.helloLottery.domain.rule.model.vo.TreeNodeVO;
import com.helloLottery.domain.rule.repository.IRuleRepository;
import com.helloLottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liujun
 * @description:
 * @date 2025/6/7 21:45
 */
@Service("ruleEngineHandle")
public class RuleEngineHandler extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {

        Long treeId = matter.getTreeId();
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(treeId);
        if (null == treeRuleRich) {
            throw new RuntimeException("Tree Rule is null!");
        }

        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);

        return new EngineResult(matter.getUserId(), treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}
