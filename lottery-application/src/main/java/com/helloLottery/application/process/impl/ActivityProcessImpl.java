package com.helloLottery.application.process.impl;

import com.helloLottery.application.process.IActivityProcess;
import com.helloLottery.application.process.req.DrawProcessReq;
import com.helloLottery.application.process.res.DrawProcessResult;
import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;
import com.helloLottery.domain.activity.model.vo.DrawOrderVO;
import com.helloLottery.domain.activity.service.partake.IActivityPartake;
import com.helloLottery.domain.strategy.model.req.DrawReq;
import com.helloLottery.domain.strategy.model.res.DrawResult;
import com.helloLottery.domain.strategy.model.vo.DrawAwardInfo;
import com.helloLottery.domain.strategy.service.draw.IDrawExec;
import com.helloLottery.domain.support.ids.IIdGenerator;
import com.hellolottery.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liujun
 * @description:
 * @date 2025/6/6 10:27
 */
@Service
public class ActivityProcessImpl  implements IActivityProcess {

    @Resource
    private IDrawExec drawExec;

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!partakeResult.getCode().equals(Constants.ResponseCode.SUCCESS.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }

        // 开始抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), partakeResult.getStrategyId(), String.valueOf(partakeResult.getTakeId())));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();

        // 保存记录
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, partakeResult.getStrategyId(), partakeResult.getTakeId(), drawAwardInfo));

        // 发送奖品

        // 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardInfo drawAwardInfo) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }

}
