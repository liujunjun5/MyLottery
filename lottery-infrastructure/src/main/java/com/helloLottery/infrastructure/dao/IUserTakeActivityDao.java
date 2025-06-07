package com.helloLottery.infrastructure.dao;

import com.helloLottery.infrastructure.po.UserTakeActivity;
import com.mars.middleware.db.router.annotation.DBRouter;
import com.mars.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liujun
 * @description: 用户参与活动表
 * @date 2025/5/29 20:09
 */
//@DBRouterStrategy(splitTable = true)
@Mapper
public interface IUserTakeActivityDao {

    /***
     * @description 插入用户参与活动信息
     * @author liujun
     * @date 20:19 2025/5/29
     * @param userTakeActivity
     **/
//    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录
     *
     * @param userTakeActivity  入参
     * @return                  更新结果
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * 查询此活动ID，用户最早领取但未消费的一条记录
     *
     * @param userTakeActivity 请求入参
     * @return                 领取结果
     */
    @DBRouter
    UserTakeActivity    queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);
}
