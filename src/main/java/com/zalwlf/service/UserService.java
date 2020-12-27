package com.zalwlf.service;

import com.zalwlf.po.User;
/**
 * platform
 * <p>用户业务服务规范</p>
 *
 * @author : lcq
 * @date : 2020-09-12 16:22
 **/
public interface UserService {
    /**
     * 检查登录
     * @param username 用户名
     * @param password 加密后密码
     * @return USer
     */
    User checkUser(String username,String password);


}
