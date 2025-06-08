package com.helloLottery.domain.rule.model.aggregates;

import com.helloLottery.domain.rule.model.vo.TreeNodeVO;
import com.helloLottery.domain.rule.model.vo.TreeRootVO;

import java.util.Map;

/**
 * @author liujun
 * @description: 规则树聚合
 * @date 2025/6/7 11:40
 */
public class TreeRuleRich {

    /** 树根信息 */
    private TreeRootVO treeRoot;
    /** 树节点ID -> 子节点 */
    private Map<Long, TreeNodeVO> treeNodeMap;

    public TreeRootVO getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRootVO treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNodeVO> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNodeVO> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }

}
