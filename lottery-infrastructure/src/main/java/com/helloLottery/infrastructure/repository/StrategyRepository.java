package com.helloLottery.infrastructure.repository;

import com.helloLottery.domain.activity.model.vo.StrategyVO;
import com.helloLottery.domain.strategy.model.aggregates.StrategyRich;
import com.helloLottery.domain.strategy.model.vo.DrawAwardInfo;
import com.helloLottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.helloLottery.domain.strategy.repository.IStrategyRepository;
import com.helloLottery.infrastructure.dao.IAwardDao;
import com.helloLottery.infrastructure.dao.IStrategyDao;
import com.helloLottery.infrastructure.dao.IStrategyDetailDao;
import com.helloLottery.infrastructure.po.Award;
import com.helloLottery.infrastructure.po.Strategy;
import com.helloLottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lj
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyVO strategyVO = new StrategyVO();
        BeanUtils.copyProperties(strategy, strategyVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail: strategyDetails) {
            StrategyDetailBriefVO strategyDetailVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailVO);
            strategyDetailBriefVOList.add(strategyDetailVO);
        }

        return new StrategyRich(strategyId, strategyVO, strategyDetailBriefVOList);
    }

    @Override
    public DrawAwardInfo queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo();
        BeanUtils.copyProperties(award, drawAwardInfo);
        return drawAwardInfo;
    }

    @Override
    public List<String> queryNoStackStrategyAwareList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);
        return count == 1;
    }
}
