package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.Blog;
import com.zalwlf.po.Tag;
import com.zalwlf.po.Type;
import com.zalwlf.service.BlogService;
import com.zalwlf.service.TagService;
import com.zalwlf.vo.BlogQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * platform
 * <p>标签接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-29 18:28
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/tag")
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String tags(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Tag> tags = tagService.listTagTop(99999999, "blogs.size");
        if (id == -1L){
            id = (tags == null || tags.get(0) == null) ? -1L:tags.get(0).getId();
        }
        Page<Blog> blogs = blogService.listBlog(pageable, id);
        model.addAttribute("tags",tags);
        model.addAttribute("blogs",blogs);
        model.addAttribute("activeTagId", id);
        return "tags";
    }


}
