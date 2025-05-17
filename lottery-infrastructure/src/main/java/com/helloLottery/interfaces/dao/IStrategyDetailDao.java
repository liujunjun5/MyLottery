package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);
}
