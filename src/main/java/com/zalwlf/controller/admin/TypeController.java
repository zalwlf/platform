package com.zalwlf.controller.admin;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.Type;
import com.zalwlf.service.TypeService;
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
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P+"/admin/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(
            @PageableDefault(size = 5, sort = {Constants.PLATFORM_BLOG_V_FIELD_ID},direction = Sort.Direction.DESC) Pageable pageable,
            Model model){
        model.addAttribute("page",typeService.listTypePage(pageable));
        return "admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id")Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types/save")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/types-input";
        }
        if (typeService.getTypeByName(type.getName()).size() > 0){
            result.rejectValue("name","nameError","不能重复添加分类");
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/type/types";
    }

    @PostMapping("/types/{id}/update")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable("id") Long id, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/types-input";
        }
        List<Type> types = typeService.getTypeByName(type.getName());
        if (types.size() > 0){
            if (types.size() == 1 && types.get(0).getId().equals(id)){
                attributes.addFlashAttribute("message","操作成功");
                return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/type/types";
            }
            result.rejectValue("name","nameError","不能重复添加分类");
            return "admin/types-input";
        }

        Type t = typeService.updateType(id, type);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/type/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P+"/admin/type/types";
    }

}
