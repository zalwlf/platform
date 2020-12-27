package com.zalwlf.po;

import org.junit.jupiter.api.Test;

/**
 * platform
 * <p>jpa实体类使用测试</p>
 *
 * @author : lcq
 * @date : 2020-09-10 21:59
 **/
public class JpaEntityTest {

    @Test
    public void toStringTest(){
        Tag tag = new Tag();
        tag.setId(11223344556677L);
        tag.setName("TEST-VALUE");
        System.out.println(tag.toString());
    }

}
