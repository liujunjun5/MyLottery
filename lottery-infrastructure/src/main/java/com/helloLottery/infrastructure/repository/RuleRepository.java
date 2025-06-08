package com.helloLottery.infrastructure.repository;

import com.helloLottery.domain.rule.model.aggregates.TreeRuleRich;
import com.helloLottery.domain.rule.model.vo.TreeNodeLineVO;
import com.helloLottery.domain.rule.model.vo.TreeNodeVO;
import com.helloLottery.domain.rule.model.vo.TreeRootVO;
import com.helloLottery.domain.rule.repository.IRuleRepository;
import com.helloLottery.infrastructure.dao.IRuleTreeDao;
import com.helloLottery.infrastructure.dao.IRuleTreeNodeDao;
import com.helloLottery.infrastructure.dao.IRuleTreeNodeLineDao;
import com.helloLottery.infrastructure.po.RuleTree;
import com.helloLottery.infrastructure.po.RuleTreeNode;
import com.helloLottery.infrastructure.po.RuleTreeNodeLine;
import com.hellolottery.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @description: 从数据库加载完整的规则树结构（包括树根、节点和节点连接线），将其转换为内存中的数据结构（TreeRuleRich）
 * @date 2025/6/7 20:40
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private IRuleTreeDao ruleTreeDao;
    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;
    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        // 1. 加载规则树根节点
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRoot.setTreeName(ruleTree.getTreeName());

        // 2. 加载所有树节点
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeDao.queryRuleTreeNodeList(treeId);

        // 3. 遍历处理每个节点
        for (RuleTreeNode treeNode : ruleTreeNodeList) {
            List<TreeNodeLineVO> treeNodeLineInfoList = new ArrayList<>();

            // 4. 如果是枝节点，加载其连接线
            if (Constants.NodeType.STEM.equals(treeNode.getNodeType())) {
                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(treeNode.getId());

                List<RuleTreeNodeLine> ruleTreeNodeLineList =
                        ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                // 5. 转换连接线数据结构
                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLineInfo = new TreeNodeLineVO();
                    treeNodeLineInfo.setNodeIdFrom(nodeLine.getNodeIdFrom());
                    treeNodeLineInfo.setNodeIdTo(nodeLine.getNodeIdTo());
                    treeNodeLineInfo.setRuleLimitType(nodeLine.getRuleLimitType());
                    treeNodeLineInfo.setRuleLimitValue(nodeLine.getRuleLimitValue());
                    treeNodeLineInfoList.add(treeNodeLineInfo);
                }
            }

            // 6. 构建树节点VO
            TreeNodeVO treeNodeInfo = new TreeNodeVO();
            treeNodeInfo.setTreeId(treeNode.getTreeId());
            treeNodeInfo.setTreeNodeId(treeNode.getId());
            treeNodeInfo.setNodeType(treeNode.getNodeType());
            treeNodeInfo.setNodeValue(treeNode.getNodeValue());
            treeNodeInfo.setRuleKey(treeNode.getRuleKey());
            treeNodeInfo.setRuleDesc(treeNode.getRuleDesc());
            treeNodeInfo.setTreeNodeLineInfoList(treeNodeLineInfoList);

            treeNodeMap.put(treeNode.getId(), treeNodeInfo);
        }

        // 7. 组装完整规则树
        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRoot);
        treeRuleRich.setTreeNodeMap(treeNodeMap);

        return treeRuleRich;
    }
}
