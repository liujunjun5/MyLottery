package com.helloLottery.application.process.res;

import com.helloLottery.domain.strategy.model.vo.DrawAwardVO;
import com.hellolottery.common.Result;

/**
 * @author liujun
 * @description: 抽奖结果
 * @date 2025/6/6 10:21
 */
public class DrawProcessResult extends Result {
    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}
