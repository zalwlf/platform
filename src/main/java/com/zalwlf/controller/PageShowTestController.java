package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * platform
 * <p>测试页面引擎展示</p>
 *
 * @author : lcq
 * @date : 2020-09-11 23:43
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P +"/page")
public class PageShowTestController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/admin/blogs")
    public String aBlogs(){
        return "admin/blogs";
    }

    @GetMapping("/admin/blogs_publish")
    public String aBlogPublish(){
        return "blogs-input";
    }

    @GetMapping("/admin/index")
    public String aIndex(){
        return "admin/index";
    }

    @GetMapping("/admin/login")
    public String aLogin(){
        return "admin/login";
    }

}
