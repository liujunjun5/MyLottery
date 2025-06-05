package com.helloLottery.infrastructure.repository;

import com.helloLottery.domain.activity.repository.IUserTakeActivityRepository;
import com.helloLottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.helloLottery.infrastructure.dao.IUserTakeActivityDao;
import com.helloLottery.infrastructure.po.UserTakeActivity;
import com.helloLottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author liujun
 * @description:
 * @date 2025/5/30 17:50
 */
@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        if (null == userTakeLeftCount) {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount - 1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        } else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setTakeDate(takeDate);
        userTakeActivity.setActivityName(activityName);
        if (userTakeLeftCount == null) {
            userTakeActivity.setTakeCount(1);
        } else {
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount);
        }
        userTakeActivity.setUuid(uId + "_" + activityId + "_" + userTakeActivity.getTakeCount());

        userTakeActivityDao.insert(userTakeActivity);
    }
}
