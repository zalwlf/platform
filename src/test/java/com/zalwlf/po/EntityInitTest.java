package com.zalwlf.po;

import org.junit.jupiter.api.Test;

/**
 * platform
 * <p>实体初始化方法测试</p>
 *
 * @author : lcq
 * @date : 2020-09-21 10:35
 **/
public class EntityInitTest {

    public void init(){
        System.out.println("123,初始化方法执行了....");
    }

    public EntityInitTest() {
        System.out.println("123,构造方法执行了....");
    }

    @Test
    public void testMethod(){
        EntityInitTest test = new EntityInitTest();
    }


}
