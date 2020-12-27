package com.zalwlf.common.util;

import java.util.Collection;

/**
 * platform
 * <p>数据验证</p>
 *
 * @author : lcq
 * @date : 2020-09-04 12:29
 **/
public class Verify {

    public static boolean isNotNull(Object o){
        return o != null;
    }

    public static boolean isNull(Object o){
        return o == null;
    }

    public static boolean isBlank(String s){
        return s == null || s.length() == 0;
    }

    public static boolean isNotBlank(String s){
        return !isBlank(s);
    }

    public static boolean isDigit(String s){
        return isNotBlank(s) && s.matches("[0-9]+");
    }

    public static boolean isNotDigit(String s){
        return !isDigit(s);
    }

    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }




}
