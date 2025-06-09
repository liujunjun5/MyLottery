package com.helloLottery.application.work;

import com.alibaba.fastjson.JSON;
import com.helloLottery.domain.activity.model.vo.ActivityVO;
import com.helloLottery.domain.activity.service.deploy.IActivityDeploy;
import com.helloLottery.domain.activity.service.stateflow.IStateHandle;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author liujun
 * @description: 活动状态扫描xxl-job 执行器
 * @date 2025/6/9 21:19
 */
@Component
public class LotteryXxlJob {

    private Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);

    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandle stateHandle;

    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() throws Exception {

        logger.info("开始活动状态扫描");
        logger.info("-----------------");

        List<ActivityVO> activityList = activityDeploy.scanToDoActivityList(0L);
        if (activityList.isEmpty()) {
            logger.info("暂无活动需要处理");
            return;
        }

        while (!activityList.isEmpty()) {
            for (ActivityVO activityVO : activityList) {
                switch (activityVO.getState()) {
                    // 扫描审核通过的活动，从活动通过到活动进行
                    case 4:
                        Result state4Result = stateHandle.doing(activityVO.getActivityId(), Constants.ActivityState.PASS);
                        logger.info("扫描活动状态为活动中 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state4Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        break;
                    // 扫描时间已过期的活动，从活动中状态变更为关闭状态
                    case 5:
                        if (activityVO.getEndDateTime().before(new Date())){
                            Result state5Result = stateHandle.close(activityVO.getActivityId(), Constants.ActivityState.DOING);
                            logger.info("扫描活动状态为关闭 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state5Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        }
                        break;
                    default:
                        break;
                }
            }
            // 获取集合中最后一条记录，继续扫描后面10条记录
            ActivityVO activityVO = activityList.get(activityList.size() - 1);
            activityList = activityDeploy.scanToDoActivityList(activityVO.getId());
        }

        logger.info("扫描活动状态完成");
        logger.info("-----------------");
    }

}
