package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {
    /**
     * 查询策略对应的明细
     * @param strategyId
     * @return 策略明细列表
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询策略没有库存的奖品列表
     * @param strategyId
     * @return
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减奖品库存
     * @param strategyDetailReq 包括策略id、奖品id
     * @return
     */
    int deductStock(StrategyDetail strategyDetailReq);

    /***
     * @description: 插入策略明细
     * @param: list
     * @author liujun
     * @date: 2025/5/25 11:02
     */
    void insertList(List<StrategyDetail> list);
}
