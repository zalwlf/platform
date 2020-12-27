package com.zalwlf.dao;

import com.zalwlf.po.Comment;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * platform
 * <p>评论ORM</p>
 *
 * @author : lcq
 * @date : 2020-09-28 21:49
 **/
public interface CommentRepository extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {

    /**
     * 查找博客的评论
     * @param blogId 博客主键 {@link Long}
     * @param sort 排序规则 {@link Sort}
     * @return {@link List<Comment>}
     */
    List<Comment> findByBlogId(Long blogId, Sort sort);

    /**
     * 查找博客的最上级评论
     * @param blogId 博客主键 {@link Long}
     * @param sort 排序规则 {@link Sort}
     * @return {@link List<Comment>}
     */
    List<Comment> findByBlogIdAndParentCommentIsNull(Long blogId, Sort sort);


}
