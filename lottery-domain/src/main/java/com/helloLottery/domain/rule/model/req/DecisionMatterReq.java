package com.helloLottery.domain.rule.model.req;

import java.util.Map;

/**
 * @author liujun
 * @description: 规则过滤入参
 * @date 2025/6/7 11:31
 */
public class DecisionMatterReq {

    /**规则树id*/
    private Long treeId;

    /**用户id*/
    private String userId;

    /**用户配置信息*/
    private Map<String, Object> valMap;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
