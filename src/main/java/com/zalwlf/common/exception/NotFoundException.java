package com.zalwlf.common.exception;

import com.zalwlf.common.result.IErrorCode;
import com.zalwlf.common.result.ResultCode;

/**
 * platform
 * <p>自定义异常,资源找不到问题处理</p>
 *
 * @author : lcq
 * @date : 2020-09-13 00:05
 **/
public class NotFoundException extends ApiException {

    public NotFoundException() {
        super(ResultCode.VALIDATE_FAILED);
    }

    public NotFoundException(String message) {
        super(new IErrorCode() {
            @Override
            public long getCode() {
                return 404;
            }

            @Override
            public String getMessage() {
                return message;
            }
        });
    }

    public NotFoundException(Throwable cause) {
        super(ResultCode.VALIDATE_FAILED, cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(new IErrorCode() {
            @Override
            public long getCode() {
                return 404;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }, cause);
    }

}
