package com.helloLottery.infrastructure;

import com.helloLottery.rpc.IAcitivityBooth;
import com.helloLottery.rpc.req.ActivityReq;
import com.helloLottery.rpc.res.ActivityRes;

/**
 * @author: zhuxuefei
 * @Description:

 */
public class ActivityBooth implements IAcitivityBooth{

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        return null;
    }
}
