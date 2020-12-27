package com.zalwlf.common.result;

/**
 * 封装API的错误码
 * @author macro,lcq
 * @date 2019/4/19
 */
public interface IErrorCode {
    /**
     * 返回结果编码,通常是200,500,404等
     * @return long
     */
    long getCode();

    /**
     * 返回结果描述信息
     * @return String
     */
    String getMessage();
}
