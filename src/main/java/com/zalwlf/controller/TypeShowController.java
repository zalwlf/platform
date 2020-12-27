package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.Blog;
import com.zalwlf.po.Type;
import com.zalwlf.service.BlogService;
import com.zalwlf.service.TypeService;
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
 * <p>分类接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-29 17:54
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/type")
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String types(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(99999999, "blogs.size");
        if (id == -1L){
            id = (types == null || types.get(0) == null) ? -1L:types.get(0).getId();
        }
        BlogQuery query = new BlogQuery();
        query.setTypeId(id);
        Page<Blog> blogs = blogService.listBlog(pageable, query);
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
