package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {
    /***
     * @description: 查询奖品信息
     * @param: awardId
     * @return: Award
     * @author liujun
     * @date: 2025/5/25 10:57
     */
    Award queryAwardInfo(String awardId);

    /***
     * @description: 插入奖品信息
     * @param: list
     * @author liujun
     * @date: 2025/5/25 10:59
     */
    void insertList(List<Award> list);
}
