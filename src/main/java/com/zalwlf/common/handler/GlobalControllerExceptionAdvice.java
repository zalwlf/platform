package com.zalwlf.common.handler;

import com.zalwlf.common.exception.ApiException;
import com.zalwlf.common.exception.ExceptionUtils;
import com.zalwlf.common.exception.NotFoundException;
import com.zalwlf.common.result.CommonResult;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * platform
 * <p>controller全局异常处理</p>
 * @author : lcq
 * @date : 2020-09-02 17:41
 **/
@Log4j2
@ControllerAdvice
public class GlobalControllerExceptionAdvice {

    @Value("${exception.handler.packageSelector}")
    private String[] packageSelector;

    @ExceptionHandler(value = ApiException.class)
    public ModelAndView handleApiException(HttpServletRequest request, ApiException e) {
        if (log.isErrorEnabled()) {
            log.error("Request URL : {}, Exception : {}",request.getRequestURI(), ExceptionUtils.getIncludedStackTrace(e,packageSelector));
        }
        if (e.getErrorCode() != null) {
            return new ModelAndView(e.getErrorCode().getCode() == 404L?"error/404":"error/error","result",CommonResult.failed(e.getErrorCode()));
        }
        return new ModelAndView("error/error","result",CommonResult.failed(e.getMessage()));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView handleNullPointerException(HttpServletRequest request, Exception e) {
        if (log.isErrorEnabled()) {
            log.error("Request URL : {}, Exception : {}",request.getRequestURI(),ExceptionUtils.getIncludedStackTrace(e,packageSelector));
        }
        return new ModelAndView("error/error","result",CommonResult.failed(e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleOtherException(HttpServletRequest request, Exception e) {
        if (log.isErrorEnabled()) {
            log.error("Request URL : {}, Exception : {}",request.getRequestURI(),ExceptionUtils.getFullStackTrace(e));
        }
        return new ModelAndView("error/error","result",CommonResult.failed(e.getMessage()));
    }

}
