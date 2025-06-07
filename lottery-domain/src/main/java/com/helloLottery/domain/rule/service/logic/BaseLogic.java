package com.helloLottery.domain.rule.service.logic;

import com.helloLottery.domain.rule.model.vo.TreeNodeLineVO;
import com.hellolottery.common.Constants;

import java.util.List;

/**
 * @author liujun
 * @description:
 * @date 2025/6/7 21:26
 */
public abstract class BaseLogic implements LogicFilter {

    /**
     * @description: 获取下一个节点
     * @author liujun
     * @date 2025/6/7 21:30
     */
    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineVOList) {
        for (TreeNodeLineVO treeNodeLine : treeNodeLineVOList) {
            if (decisionLogic(matterValue, treeNodeLine)) {
                return treeNodeLine.getNodeIdTo();
            }
        }
        return Constants.Global.TREE_NULL_NODE;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }
}
