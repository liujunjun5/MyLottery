package com.helloLottery.rpc.res;

import cn.itedus.lottery.common.Result;
import com.helloLottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * @author liujun
 * @description: 抽奖结果
 * @date 2025/6/8 20:28
 */
public class DrawRes extends Result implements Serializable {
    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }

}
