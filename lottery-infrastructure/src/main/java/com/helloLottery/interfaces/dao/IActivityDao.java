package com.helloLottery.interfaces.dao;

import com.helloLottery.domain.activity.model.vo.AlterStateVO;
import com.helloLottery.interfaces.po.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityDao {

    /***
     * @description: 插入活动配置
     * @param: req
     * @return: void
     * @author liujun
     * @date: 2025/5/25 11:02
     */
    void insert(Activity req);

    /***
     * @description: 根据活动id查询活动配置
     * @param: activityId
     * @return: com.helloLottery.infrastructure.po.Activity
     * @author liujun
     * @date: 2025/5/25 11:03
     */
    Activity queryActivityById(Long activityId);

    /***
     * @description: 变更活动状态
     * @param: alterStateVO
     * @return: int 更新数量
     * @author liujun
     * @date: 2025/5/25 11:07
     */
    int alterState(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);
}