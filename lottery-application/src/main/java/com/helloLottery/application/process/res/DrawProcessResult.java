package com.helloLottery.application.process.res;

import com.helloLottery.domain.strategy.model.vo.DrawAwardInfo;
import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 抽奖结果
 * @date 2025/6/6 10:21
 */
public class DrawProcessResult extends Result {
    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}
