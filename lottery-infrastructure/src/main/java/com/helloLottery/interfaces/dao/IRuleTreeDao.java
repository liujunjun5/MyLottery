package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liujun
 * @description:
 * @date 2025/6/7 21:09
 */
@Mapper
public interface IRuleTreeDao {

    /**
     * 规则树查询
     * @param id ID
     * @return   规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     * @param treeId 规则树ID
     * @return       规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);


}
