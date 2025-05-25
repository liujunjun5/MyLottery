package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {
    /*** 
     * @description: 查询策略信息 
     * @param: strategyId 
     * @return: com.helloLottery.infrastructure.po.Strategy 
     * @author liujun
     * @date: 2025/5/25 11:00
     */ 
    Strategy queryStrategy(Long strategyId);
    
    /*** 
     * @description: 插入策略配置 
     * @param: req 
     * @return: void 
     * @author liujun
     * @date: 2025/5/25 11:02
     */ 
    void insertStrategy(Strategy req);
}