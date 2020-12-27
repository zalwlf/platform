package com.zalwlf.common.util;

/**
 * platform
 * <p>断言</p>
 *
 * @author : lcq
 * @date : 2020-09-04 12:23
 **/
public class Assert extends org.springframework.util.Assert {

    public static void isDigit(String s, String message){
        state(Verify.isDigit(s), message);
    }

    public static void isNotDigit(String s, String message){
        state(Verify.isNotDigit(s), message);
    }

}
