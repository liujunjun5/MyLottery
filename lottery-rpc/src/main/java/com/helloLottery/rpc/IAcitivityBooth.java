package com.helloLottery.rpc;

import com.helloLottery.rpc.req.ActivityReq;
import com.helloLottery.rpc.res.ActivityRes;

public interface IAcitivityBooth {
    ActivityRes queryActivityById(ActivityReq req);
}
