package com.helloLottery.domain.activity.repository;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.vo.*;
import com.hellolottery.common.Constants;

import java.util.List;

/***
 * @description: 活动领域的仓储服务
 * @author liujun
 * @date: 2025/5/25 10:24
 */
public interface IActivityRepository {

    /**
     * @param activity 活动配置
     * @description: 添加活动配置
     * @author liujun
     * @date 2025/5/25 10:29
     */
    void addActivity(ActivityVO activity);

    /**
     * @param awardList 奖品配置集合
     * @description: 添加奖品配置
     * @author liujun
     * @date 2025/5/25 10:30
     */
    void addAward(List<AwardVO> awardList);


    /**
     * @param strategy 策略配置
     * @description: 添加策略配置
     * @author liujun
     * @date 2025/5/25 10:30
     */
    void addStrategy(StrategyVO strategy);

    /**
     * @param strategyDetailList 策略明细集合
     * @description: 添加策略明细配置
     * @author liujun
     * @date 2025/5/25 10:39
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    /**
     * @param activityId  活动ID
     * @param beforeState 修改前状态
     * @param afterState  修改后状态
     * @return 更新结果
     * @description: 变更活动状态
     * @author liujun
     * @date 2025/5/25 10:38
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);

    /***
     * @description 查询活动账单
     * @author liujun
     * @date 17:35 2025/5/30
     * @return com.helloLottery.domain.activity.model.vo.ActivityBillVO
     **/
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * @description 扣减活动库存
     * @param activityId   活动ID
     * @return      扣减结果
     */
    int subtractionActivityStock(Long activityId);

    /**
     * @description 扫描待处理的活动列表，状态为：通过、活动中
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<ActivityVO> scanToDoActivityList(Long id);
}
