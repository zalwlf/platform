package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.Blog;
import com.zalwlf.service.BlogService;
import com.zalwlf.service.TagService;
import com.zalwlf.service.TypeService;
import com.zalwlf.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * platform
 * <p>首页</p>
 *
 * @author : lcq
 * @date : 2020-09-23 15:24
 **/
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page", blogService.listBlog(pageable, new BlogQuery()));
        model.addAttribute("types", typeService.listTypeTop(6, "blogs.size"));
        model.addAttribute("tags", tagService.listTagTop(6, "blogs.size"));
        model.addAttribute("recommendBlogs", blogService.findTop8ByOrderByUpdateTimeDesc());
        return "index";
    }

    @GetMapping("/test/top")
    public @ResponseBody Object findTop8AndSort(){
        return blogService.findTop8ByOrderByUpdateTimeDesc();
    }

    @GetMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/index/blog/{id}")
    public String findBlog(@PathVariable Long id,Model model){
        blogService.updateBlogViews(id);
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @PostMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/search")
    public String search(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page", blogService.listBlog("%" + query + "%",pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/footer/newBlog")
    public String footerBlogs(Model model){
        model.addAttribute("newBlogs", blogService.findTop3ByOrderByUpdateTimeDesc());
        return "_fragments :: newBlogList";
    }

}
