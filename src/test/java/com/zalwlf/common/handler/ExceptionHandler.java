package com.zalwlf.common.handler;

import com.zalwlf.common.exception.ExceptionUtils;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

/**
 * platform
 * <p>异常处理器测试类</p>
 *
 * @author : lcq
 * @date : 2020-09-02 23:48
 **/
public class ExceptionHandler {

    public void testMethod1(){
        System.out.println("异常准备...");
        int i = 9/0;
    }

    public void testMethod2(){
        int i = 1;
        i = i+2;
        testMethod1();
    }
    public void testMethod3(){
        System.out.println("--------------------");
        testMethod2();
    }
    public void testMethod4(){
        int i = 3;
        i++;
        i++;
        i--;
        testMethod3();
    }
    @Test
    public void runMethod(){
        try {
            testMethod4();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                System.out.println(element.toString());
            }
            System.out.println("--------------------------------------------------------");
            GlobalControllerExceptionAdvice advice = new GlobalControllerExceptionAdvice();
            String trace = ExceptionUtils.getIncludedStackTrace(e, new String[]{"com.zalwlf"});
            System.out.println(trace);
        }
    }

    @Test
    public void zeroArrayToSet(){
        String[] strings = new String[0];
        HashSet<String> set = new HashSet<>(Arrays.asList(strings));
        System.out.println(set.size());
    }


}
