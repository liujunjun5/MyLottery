package com.helloLottery.interfaces.repository;

import com.helloLottery.domain.award.repository.IAwardRepository;
import com.helloLottery.interfaces.dao.IUserStrategyExportDao;
import com.helloLottery.interfaces.po.UserStrategyExport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liujun
 * @date: 2025-6-9 15:18:21
 * @descrition: 奖品仓储
 */
@Component
public class AwardRepository implements IAwardRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);
    }
}
