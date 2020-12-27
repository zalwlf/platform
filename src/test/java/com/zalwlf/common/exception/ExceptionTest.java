package com.zalwlf.common.exception;

import org.junit.jupiter.api.Test;

/**
 * platform
 * <p>异常测试</p>
 *
 * @author : lcq
 * @date : 2020-10-13 16:37
 **/
public class ExceptionTest {

    public String testExceptionMethodOne(){
        String result = "";
        try {
            result = String.valueOf(4/0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void tetExceptionMethodRun(){
        try {
            System.out.println("-------------------------");
            String methodOne = testExceptionMethodOne();
            System.out.println("----------= "+methodOne+" =-------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
