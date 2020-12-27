package com.zalwlf.controller;

import com.zalwlf.common.exception.ApiException;
import com.zalwlf.common.result.CommonResult;
import com.zalwlf.po.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * platform
 * <p>控制层基类</p>
 *
 * @author : lcq
 * @date : 2020-09-02 18:31
 **/
@RestController
@RequestMapping("/base")
public class BaseController {

    @GetMapping("/test")
    public Object test(){
        return CommonResult.success();
    }
    @GetMapping("/testException")
    public Object testException(){
        throw new NullPointerException("this data is null");
    }
    @GetMapping("/testException2")
    public Object testException2(){
        int i = 9/0;
        return CommonResult.success();
    }
    @GetMapping("/testException3")
    public Object testException3(){
        throw new ApiException("this data is null");
    }

    @GetMapping("/testException4")
    public Object testException4(){
        Comment comment = new Comment();
        comment.setBlogger(true);
        comment.setNickname("张三");
        return comment;
    }
}
