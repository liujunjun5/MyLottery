package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.Activity;

public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}