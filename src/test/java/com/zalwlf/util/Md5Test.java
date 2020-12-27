package com.zalwlf.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

/**
 * platform
 * <p>Md5加密测试</p>
 *
 * @author : lcq
 * @date : 2020-09-12 19:00
 **/
public class Md5Test {

    @Test
    public void testSpringMd5(){
        String password = "123456";
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

}
