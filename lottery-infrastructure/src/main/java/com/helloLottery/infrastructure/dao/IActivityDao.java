package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}