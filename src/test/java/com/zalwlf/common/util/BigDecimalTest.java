package com.zalwlf.common.util;

import java.math.BigDecimal;

/**
 * platform
 * <p>金额类数字精度测试</p>
 *
 * @author : lcq
 * @date : 2020-10-10 12:10
 **/
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("0E-8");
        System.out.println(decimal.toPlainString());
        System.out.println(decimal.toString());
        System.out.println(decimal.toEngineeringString());
        System.out.println(decimal);
    }

}
