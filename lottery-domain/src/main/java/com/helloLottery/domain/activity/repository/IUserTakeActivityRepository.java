package com.helloLottery.domain.activity.repository;

import com.helloLottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.helloLottery.domain.activity.model.vo.DrawOrderVO;
import com.helloLottery.domain.activity.model.vo.InvoiceVO;
import com.helloLottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;
import java.util.List;

/**
 * @author liujun
 * @description: 用户参与活动仓储接口
 * @date 2025/5/30 17:42
 */
public interface IUserTakeActivityRepository {

    /**
     * 扣减个人活动参与次数
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @return                  更新结果
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId);

    /**
     * 参与活动
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         活动个人可参与次数
     * @param userTakeLeftCount 活动个人剩余参与次数
     * @param uId               用户ID
     * @param takeDate          参与时间
     * @param takeId            领取ID
     */
    void takeActivity(Long activityId, String activityName, Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     *
     * @param activityId    活动ID
     * @param uId           用户ID
     * @return              领取单
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    /**
     * 锁定活动领取记录
     *
     * @param uId        用户ID
     * @param activityId 活动ID
     * @param takeId     领取ID
     * @return 更新结果
     */
    int lockTackActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     *
     * @param drawOrder 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrder);

    /**
     * 更新发货单MQ状态
     *
     * @param uId     用户ID
     * @param orderId 订单ID
     * @param mqState MQ 发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 扫描发货单 MQ 状态，把未发送 MQ 的单子扫描出来，做补偿
     *
     * @return 发货单
     */
    List<InvoiceVO> scanInvoiceMqState();

    void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO);
}
