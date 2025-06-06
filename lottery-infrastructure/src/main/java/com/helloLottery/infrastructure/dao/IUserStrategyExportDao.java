package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.UserStrategyExport;
import com.mars.middleware.db.router.annotation.DBRouter;
import com.mars.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liujun
 * @description: 用户策略计算结果表DAO
 * @date 2025/5/29 21:37
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId 用户ID
     * @return 用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);

}
