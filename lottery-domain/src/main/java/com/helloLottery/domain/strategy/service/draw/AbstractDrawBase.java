package com.helloLottery.domain.strategy.service.draw;

import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.model.req.DrawReq;
import com.helloLottery.domain.strategy.model.res.DrawResult;
import com.helloLottery.domain.strategy.model.vo.AwardRateInfo;
import com.helloLottery.domain.strategy.model.vo.DrawAwardInfo;
import com.helloLottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.helloLottery.infrastructure.po.Award;
import com.helloLottery.infrastructure.po.Strategy;
import com.helloLottery.infrastructure.po.StrategyDetail;
import com.hellolottery.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lj
 * 模板方式 定义抽奖流程
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //  获取对应的抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        //  基本的校验以及初始化
        this.checkAndInitRateData(strategy.getStrategyId(), strategyRich.getStrategyDetailList(), strategy.getStrategyMode());
        //  获取被排除的奖品id（基于库存、风控等）
        List<String> excludeAwardIds = this.queryExcludeAwardIds(strategyRich.getStrategyId());
        //  执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);
        //  包装结果类
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId);
    }


    /**
     * 编排查询对应的策略不需要的奖品id
     *
     * @param strategyId
     * @return
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖策略
     *
     * @param strategyId
     * @param iDrawAlgorithm
     * @param excludeAwardIds
     * @return 中奖结果
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经被初始化到内存
     *
     * @param strategyId
     * @param strategyDetailList
     * @param strategyMode
     */
    private void checkAndInitRateData(Long strategyId, List<StrategyDetail> strategyDetailList, Integer strategyMode) {
        // 不是使用元组初始化的
//        if (strategyMode != 1) {
//            return;
//        }
        if (!strategyMode.equals(Constants.StrategyMode.SINGLE.getCode())) {
            return;
        }
        // 已经初始化完了
        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);

        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if (existRateTuple) {
            return;
        }

        // 将 StrategyDetail 转换为 awardRateInfoList
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }

    /**
     * 包装中奖结果（用户id、策略id、奖品id）
     *
     * @param uId 用户id
     * @param strategyId 策略id
     * @param awardId 奖品id
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {
        Award awardInfo = super.queryAwardInfoByAwardId(awardId);
        logger.info("抽奖结果如下，用户:{},策略id:{},奖品id:{},奖品名称:{}", uId, strategyId, awardId, awardInfo.getAwardName());
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(awardId, awardInfo.getAwardName());
        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);
    }
}
