package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}