package com.helloLottery.domain.activity.service.partake;

import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.vo.ActivityBillVO;
import com.helloLottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author liujun
 * @description:
 * @date 2025/5/30 16:49
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }

}
