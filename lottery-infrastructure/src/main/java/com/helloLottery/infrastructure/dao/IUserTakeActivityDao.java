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

}
