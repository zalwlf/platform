package com.zalwlf.controller.admin;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.Tag;
import com.zalwlf.service.TagService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * platform
 * <p>分类接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-13 01:13
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(
            @PageableDefault(size = 5, sort = {Constants.PLATFORM_BLOG_V_FIELD_ID},direction = Sort.Direction.DESC) Pageable pageable,
            Model model){
        model.addAttribute("page",tagService.listTagPage(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id")Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags/save")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        if (tagService.getTagByName(tag.getName()).size() > 0){
            result.rejectValue("name","nameError","不能重复添加标签");
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/tag/tags";
    }

    @PostMapping("/tags/{id}/update")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable("id") Long id, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        List<Tag> tags = tagService.getTagByName(tag.getName());
        if (tags.size() > 0){
            if (tags.size() == 1 && tags.get(0).getId().equals(id)){
                attributes.addFlashAttribute("message","操作成功");
                return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/tag/tags";
            }
            result.rejectValue("name","nameError","不能重复添加标签");
            return "admin/tags-input";
        }

        Tag t = tagService.updateTag(id, tag);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/tag/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/tag/tags";
    }

}
