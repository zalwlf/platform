package com.zalwlf.dao;

import com.zalwlf.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * platform
 * <p>用户ORM</p>
 *
 * @author : lcq
 * @date : 2020-09-12 16:22
 **/
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     *  登录检查
     * @param username 用户名
     * @param password 加密后密码
     * @return User
     */
    User findByUsernameAndPassword(String username, String password);

}
