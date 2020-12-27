package com.zalwlf.service;

import com.zalwlf.po.Comment;
import java.util.List;

/**
 * platform
 * <p>评论业务服务规范</p>
 *
 * @author : lcq
 * @date : 2020-09-28 21:36
 **/
public interface CommentService {

    /**
     * 查询博客的所有评论
     * @param blogId 博客主键{@link Long}
     * @return {@link List<Comment>}
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment 评论内容 {@link Comment}
     * @return {@link Comment}
     */
    Comment saveComment(Comment comment);

}
