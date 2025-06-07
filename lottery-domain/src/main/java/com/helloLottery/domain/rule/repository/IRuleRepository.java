package com.helloLottery.domain.rule.repository;

import com.helloLottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @author liujun
 * @description: 规则引擎仓储接口
 * @date 2025/6/7 20:38
 */
public interface IRuleRepository {

    /**
     * @description: 查询决策树配置(根信息、树映射)
     * @author liujun
     * @date 2025/6/7 20:39
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);

}
