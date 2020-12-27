package com.zalwlf.service.impl;

import com.zalwlf.dao.UserRepository;
import com.zalwlf.po.User;
import com.zalwlf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * platform
 * <p>用户业务服务</p>
 *
 * @author : lcq
 * @date : 2020-09-12 16:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }


}
