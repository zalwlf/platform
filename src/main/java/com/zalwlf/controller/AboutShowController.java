package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * platform
 * <p>关于我接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-30 16:28
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/about")
public class AboutShowController {

    @GetMapping
    public String about(){
        return "about";
    }
}
