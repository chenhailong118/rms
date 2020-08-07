package com.still.rms.auth.service;


/**
 * @Author FishAndFlower
 * @Description redis操作Service,对象和数组都以json形式进行存储
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

}
