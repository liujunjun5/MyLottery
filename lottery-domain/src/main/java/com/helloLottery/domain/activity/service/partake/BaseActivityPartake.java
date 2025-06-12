package com.helloLottery.domain.activity.service.partake;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;
import com.helloLottery.domain.activity.model.res.StockResult;
import com.helloLottery.domain.activity.model.vo.ActivityBillVO;
import com.helloLottery.domain.activity.model.vo.UserTakeActivityVO;
import com.helloLottery.domain.support.ids.IIdGenerator;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liujun
 * @description: 用户参与活动模板类
 * @date 2025/6/5 9:58
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeResult doPartake(PartakeReq req) {

        // 查询已领取但是未被消费的记录
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());

        if (null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId(), Constants.ResponseCode.NOT_CONSUMED_TAKE);
        }

        // 获取活动相关信息
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 活动信息校验
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 数据库行锁扣除方式
        // Result subStockResult = this.subActivityStock(req);

        // 活动库存的扣减
        StockResult subtractionActivityResult = this.subtractionActivityStockByRedis(req.getuId(), activityBillVO.getActivityId(), activityBillVO.getStockCount());
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), subtractionActivityResult.getCode());
            return new PartakeResult(subtractionActivityResult.getCode(), subtractionActivityResult.getInfo());
        }

        // 用户参与活动
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result takeActivityResult = this.takeActivity(req, activityBillVO, takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(takeActivityResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), takeActivityResult.getCode());
            return new PartakeResult(takeActivityResult.getCode(), takeActivityResult.getInfo());
        }

        this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), Constants.ResponseCode.SUCCESS.getCode());

        // 封装并且返回结果
        return buildPartakeResult(activityBillVO.getStrategyId(), takeId, activityBillVO.getStockCount(), subtractionActivityResult.getStockSurplusCount(), Constants.ResponseCode.SUCCESS);
    }

    /**
     * 封装结果【返回的策略ID，用于继续完成抽奖步骤】
     *
     * @param strategyId        策略ID
     * @param takeId            领取ID
     * @param stockCount        库存
     * @param stockSurplusCount 剩余库存
     * @param code              状态码
     * @return 封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Integer stockCount, Integer stockSurplusCount, Constants.ResponseCode code) {
        PartakeResult partakeResult = new PartakeResult(code.getCode(), code.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        partakeResult.setStockCount(stockCount);
        partakeResult.setStockSurplusCount(stockSurplusCount);
        return partakeResult;
    }

    /**
     * 封装结果【返回的策略ID，用于继续完成抽奖步骤】
     *
     * @param strategyId 策略ID
     * @param takeId     领取ID
     * @param code       状态码
     * @return 封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Constants.ResponseCode code) {
        PartakeResult partakeResult = new PartakeResult(code.getCode(), code.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }

    /**
     * @description: 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * @author liujun
     * @date 2025/6/6 11:05
     */
    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String getuId);
    
        /***
         * @description 基础信息校验
         * @author liujun
         * @date 10:54 2025/6/5
         * @param req 参与Id
         * @param activityBillVO 活动相关信息
         * @return com.hellolottery.common.Result
         **/
    protected abstract Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO);

    /**
     * @description: 扣减活动次数
     * @author liujun
     * @date 2025/6/5 10:54
     */
    protected abstract Result subActivityStock(PartakeReq req);

    /**
     * @description: 用户参与活动
     * @author liujun
     * @date 2025/6/5 10:55
     */
    protected abstract Result takeActivity(PartakeReq req, ActivityBillVO activityBillVO, Long takeId);

    /**
     * 扣减活动库存，通过Redis
     *
     * @param uId        用户ID
     * @param activityId 活动号
     * @param stockCount 总库存
     * @return 扣减结果
     */
    protected abstract StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);

    /**
     * 恢复活动库存，通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     *
     * @param activityId 活动ID
     * @param tokenKey   分布式 KEY 用于清理
     * @param code       状态
     */
    protected abstract void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);

}
