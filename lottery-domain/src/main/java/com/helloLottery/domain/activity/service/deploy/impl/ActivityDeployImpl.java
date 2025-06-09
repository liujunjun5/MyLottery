package com.helloLottery.domain.activity.service.deploy.impl;

import com.alibaba.fastjson.JSON;
import com.helloLottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.helloLottery.domain.activity.model.req.ActivityConfigReq;
import com.helloLottery.domain.activity.model.vo.ActivityVO;
import com.helloLottery.domain.activity.model.vo.AwardVO;
import com.helloLottery.domain.activity.model.vo.StrategyDetailVO;
import com.helloLottery.domain.activity.model.vo.StrategyVO;
import com.helloLottery.domain.activity.repository.IActivityRepository;
import com.helloLottery.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujun
 * @description:
 * @date 2025/5/25 15:23
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createActivity(ActivityConfigReq req) {

        logger.info("活动配置开始，活动id:{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {

            // 添加活动
            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            // 添加奖品
            List<AwardVO> awardList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardList);

            // 添加策略
            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            // 添加到策略明细
            List<StrategyDetailVO> strategyDetailList = activityConfigRich.getStrategy().getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);

            logger.info("活动配置添加完成，活动id:{}", req.getActivityId());
        } catch (DuplicateKeyException e) {
            logger.error("主键重复，活动id:{}，reqJson:{}", req.getActivityId(), JSON.toJSONString(req), e);
            throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {

    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        return activityRepository.scanToDoActivityList(id);
    }
}
