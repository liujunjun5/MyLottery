package com.helloLottery.infrastructure;

import com.helloLottery.infrastructure.dao.IActivityDao;
import com.helloLottery.infrastructure.po.Activity;
import com.helloLottery.rpc.IAcitivityBooth;
import com.helloLottery.rpc.dto.ActivityDto;
import com.helloLottery.rpc.req.ActivityReq;
import com.helloLottery.rpc.res.ActivityRes;
import com.hellolottery.common.Constants;
import com.hellolottery.common.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: lj
 * @Description:

 */
@Service
public class ActivityBooth implements IAcitivityBooth{

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}
