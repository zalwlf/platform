package com.zalwlf.controller;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.util.Verify;
import com.zalwlf.po.Blog;
import com.zalwlf.po.Comment;
import com.zalwlf.po.User;
import com.zalwlf.service.BlogService;
import com.zalwlf.service.CommentService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * platform
 * <p>评论</p>
 *
 * @author : lcq
 * @date : 2020-09-28 21:24
 **/
@Controller
@RequestMapping(Constants.PLATFORM_BLOG_V_PATH_P + "/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping
    public String post(Comment comment, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Blog blog = blogService.getBlog(comment.getBlog().getId());
        if (Verify.isNotNull(user) && user.getId().equals(blog.getUser().getId())){
            comment.setAvatar(user.getAvatar());
            comment.setBlogger(true);
            comment.setNickname(user.getNickname());
        }else{
            comment.setBlogger(false);
        }
        comment.setBlog(blog);
        commentService.saveComment(comment);
        return "redirect:" + Constants.PLATFORM_BLOG_V_PATH_P + "/comments/" + comment.getBlog().getId();
    }


}
