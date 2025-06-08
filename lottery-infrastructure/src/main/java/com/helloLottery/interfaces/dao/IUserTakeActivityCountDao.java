package com.helloLottery.interfaces.dao;

import com.helloLottery.interfaces.po.UserTakeActivityCount;
import com.mars.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liujun
 * @description: 用户活动参与次数表
 * @date 2025/5/30 17:14
 */
@Mapper
public interface IUserTakeActivityCountDao {

    /**
     * @description: 查询用户活动参与次数
     * @author liujun
     * @date 2025/5/30 17:17
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCount);

    /**
     * @description: 插入领取活动次数信息
     * @author liujun
     * @date 2025/5/30 17:19
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * @description: 更新领取活动次数信息
     * @author liujun
     * @date 2025/5/30 17:20
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);

}
