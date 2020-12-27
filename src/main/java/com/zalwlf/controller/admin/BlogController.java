package com.zalwlf.controller.admin;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.util.Verify;
import com.zalwlf.po.Blog;
import com.zalwlf.po.Type;
import com.zalwlf.po.User;
import com.zalwlf.service.BlogService;
import com.zalwlf.service.TagService;
import com.zalwlf.service.TypeService;
import com.zalwlf.vo.BlogQuery;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * platform
 * <p>博客管理</p>
 *
 * @author : lcq
 * @date : 2020-09-12 19:08
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = Constants.PLATFORM_BLOG_V_FIELD_ID, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        Blog blog = new Blog();
        blog.setType(new Type());
        model.addAttribute("blog", blog);
        setTypesAndTags(model);
        return "admin/blogs-input";
    }

    private void setTypesAndTags(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("blog", blogService.getBlog(id));
        setTypesAndTags(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/save")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        tagService.saveTagOnlyNames(blog.getTagIds());
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (Verify.isNull(blog.getId())){
            b = blogService.saveBlog(blog);
        }else{
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/blog/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/blog/blogs";
    }

}
