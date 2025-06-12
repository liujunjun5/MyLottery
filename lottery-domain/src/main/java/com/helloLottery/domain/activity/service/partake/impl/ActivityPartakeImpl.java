package com.helloLottery.domain.activity.service.partake.impl;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.StockResult;
import com.helloLottery.domain.activity.model.vo.*;
import com.helloLottery.domain.activity.repository.IUserTakeActivityRepository;
import com.helloLottery.domain.activity.service.partake.BaseActivityPartake;
import com.helloLottery.domain.support.ids.IIdGenerator;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;
import com.mars.middleware.db.router.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @description: 用户参与活动实现类（校验基础信息、扣除活动库存、扣减用户参与次数）
 * @date 2025/6/5 11:01
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO) {

        // 活动状态
        if (!activityBillVO.getState().equals(Constants.ActivityState.DOING.getCode())) {
            logger.warn("活动当前状态非可用 state：{}", activityBillVO.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动当前状态非可用");
        }

        // 活动日期
        if (activityBillVO.getBeginDateTime().after(req.getPartakeDate()) || req.getPartakeDate().after(activityBillVO.getEndDateTime())) {
            logger.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", activityBillVO.getBeginDateTime(), activityBillVO.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动时间范围非可用");
        }

        // 活动库存
        if (activityBillVO.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount：{}", activityBillVO.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动剩余库存非可用");
        }

        // 个人库存
        if (null != activityBillVO.getUserTakeLeftCount() && activityBillVO.getUserTakeLeftCount() <= 0) {
            logger.warn("个人领取次数非可用 userTakeLeftCount：{}", activityBillVO.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if (count == 0) {
            logger.error("活动库存扣减失败 activityId:{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result takeActivity(PartakeReq req, ActivityBillVO bill, Long takeId) {
        try {
            // 计算并且设置路由索引到ThreadLocal
            dbRouter.doRouter(req.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), req.getuId());
                    if (0 == updateCount) {
                        status.setRollbackOnly();
                        logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uId：{}", req.getActivityId(), req.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 插入活动参与次数
//                long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), req.getuId(), req.getPartakeDate(), takeId);

                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", req.getActivityId(), req.getuId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    protected StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {
        return activityRepository.subtractionActivityStockByRedis(uId, activityId, stockCount);
    }

    @Override
    protected void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        activityRepository.recoverActivityCacheStockByRedis(activityId, tokenKey, code);
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    // 锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getuId(), drawOrder.getActivityId(), drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getuId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public void updateInvoiceMqState(String uId, Long orderId, Integer mqState) {
        userTakeActivityRepository.updateInvoiceMqState(uId, orderId, mqState);
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount) {
        try {
            // 设置路由
            dbRouter.setDBKey(dbCount);
            dbRouter.setTBKey(tbCount);

            // 查询数据
            return userTakeActivityRepository.scanInvoiceMqState();
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO) {
        userTakeActivityRepository.updateActivityStock(activityPartakeRecordVO);
    }
}
