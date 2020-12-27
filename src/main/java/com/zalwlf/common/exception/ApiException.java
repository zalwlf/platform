package com.zalwlf.common.exception;

import com.zalwlf.common.result.IErrorCode;

/**
 * platform
 * <p>全局自定义API异常</p>
 * @author : lcq
 * @date : 2020-09-02 17:46
 **/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(IErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
