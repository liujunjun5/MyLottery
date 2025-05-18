package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IAwardDao {
    Award queryAwardInfo(String awardId);
}
