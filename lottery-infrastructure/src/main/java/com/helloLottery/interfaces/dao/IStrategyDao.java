package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {
    Strategy queryStrategy(Long strategyId);
}