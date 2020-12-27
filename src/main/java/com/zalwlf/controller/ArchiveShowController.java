package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * platform
 * <p>归档接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-30 15:42
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/archives")
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String archives(Model model){
        model.addAttribute("archiveMap", blogService.listBlogByYear());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }



}
