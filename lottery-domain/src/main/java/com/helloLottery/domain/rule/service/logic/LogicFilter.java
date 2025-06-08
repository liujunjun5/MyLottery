package com.helloLottery.domain.rule.service.logic;

import com.helloLottery.domain.rule.model.req.DecisionMatterReq;
import com.helloLottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @author liujun
 * @description: 规则过滤器接口
 * @date 2025/6/7 21:20
 */
public interface LogicFilter {

    /**
     * @description: 决策处理
     * @author liujun
     * @return 下一个节点 id
     * @date 2025/6/7 21:22
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineVOList);

    /**
     * @description: 获取决策值
     * @author liujun
     * @return 决策值
     * @date 2025/6/7 21:21
     */
    String matterValue(DecisionMatterReq decisionMatter);
}
