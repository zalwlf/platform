package com.zalwlf.controller.admin;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.po.User;
import com.zalwlf.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * platform
 * <p>用户接口API</p>
 *
 * @author : lcq
 * @date : 2020-09-12 16:32
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/log/in")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){

        User user = userService.checkUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user/login";
        }
    }
    @GetMapping("/log/out")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:"+Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user/login";
    }






}
