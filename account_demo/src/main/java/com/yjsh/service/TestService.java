package com.yjsh.service;


/**
 * 测试服务
 *
 * @author YYYYJSH
 * @date 2021/12/15
 */
public interface TestService {

    /**
     * 同步测试
     *
     * @return {@link Object}
     */
    public Object syncTest();


    /**
     * 测试装
     *
     * @return {@link Object}
     */
    Object testFeign(String name ,String phone,Integer page,Integer limit);
}
