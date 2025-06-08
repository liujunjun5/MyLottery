package com.helloLottery.test;

import com.alibaba.fastjson.JSON;
import com.helloLottery.domain.activity.model.req.PartakeReq;
import com.helloLottery.domain.activity.model.res.PartakeResult;
import com.helloLottery.domain.activity.service.partake.IActivityPartake;
import com.helloLottery.interfaces.dao.IUserTakeActivityDao;
import com.helloLottery.interfaces.po.UserTakeActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author liujun
 * @description:
 * @date 2025/5/29 21:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityTakeTest {

    private Logger logger = LoggerFactory.getLogger(ActivityTakeTest.class);

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Test
    public void test_insert() {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId("Ukdli109op811d"); // 1库：Ukdli109op89oi 2库：Ukdli109op811d
        userTakeActivity.setTakeId(121019889410L);
        userTakeActivity.setActivityId(100001L);
        userTakeActivity.setActivityName("测试活动");
        userTakeActivity.setTakeDate(new Date());
        userTakeActivity.setTakeCount(10);
        userTakeActivity.setUuid("Uhdgkw766120d");

        userTakeActivityDao.insert(userTakeActivity);
    }

    @Resource
    private IActivityPartake activityPartake;

    @Test
    public void test_activityPark() {
        PartakeReq req = new PartakeReq("Ukdli109op811d", 100001L);
        PartakeResult result = activityPartake.doPartake(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }
}
