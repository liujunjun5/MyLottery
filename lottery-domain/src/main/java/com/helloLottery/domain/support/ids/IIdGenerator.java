package com.helloLottery.domain.support.ids;

/**
 * @author liujun
 * @description: id生成接口
 * @date 2025/5/25 22:48
 */
public interface IIdGenerator {

    /***
     * @description 获取id
     * @author liujun
     * @date 22:49 2025/5/25
     * @return long ID
     **/
    long nextId();

}
