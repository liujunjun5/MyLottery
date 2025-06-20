package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.UserStrategyExport;
import com.mars.middleware.db.router.annotation.DBRouter;
import com.mars.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

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

    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DBRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送MQ状态
     * @param userStrategyExport 发送消息
     */
    @DBRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);

    /**
     * @description: 扫描MQ状态，将MQ发送失败或者长时间没处理的订单获取出来
     * @author liujun
     * @date 2025/6/10 10:23
     */
    List<UserStrategyExport> scanInvoiceMqState();

}
