package com.zalwlf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zalwlf.common.util.Verify;
import com.zalwlf.dao.CommentRepository;
import com.zalwlf.po.Comment;
import com.zalwlf.service.CommentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * platform
 * <p>评论业务服务</p>
 *
 * @author : lcq
 * @date : 2020-09-28 21:47
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> commentList = commentRepository
                .findByBlogIdAndParentCommentIsNull(blogId, Sort.by(Sort.Direction.ASC, "createTime"));
        return eachComment(commentList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Comment saveComment(Comment comment) {
        if (Verify.isNotNull(comment.getParentComment())){
            comment.setParentComment(commentRepository.findById(comment.getParentComment().getId()).orElse(null));
        }else{
            comment.setParentComment(null);
        }
        comment.createDefaultNewItem();
        return commentRepository.save(comment);
    }

    /**
     * 楼主式两层分级评论处理（bean复制）
     * @param comments 包含子集的顶级评论集合{@link List<Comment>}
     * @return {@link List<Comment>}
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment root = new Comment();
            BeanUtil.copyProperties(comment, root);
            commentsView.add(root);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * 楼主式两层分级评论处理
     * @param comments 包含子集的顶级评论集合{@link List<Comment>}
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment root : comments) {
            List<Comment> replyComments = new ArrayList<>(1);
            for (Comment secondaryNode : root.getReplyComments()) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(secondaryNode, replyComments);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            root.setReplyComments(replyComments);
        }
    }

    /**
     * 子集评论处理,递归
     * @param comment 被迭代的对象{@link Comment}
     * @param replyComments 二级及以下层级评论存储单元{@link List<Comment>}
     */
    private void recursively(Comment comment, List<Comment> replyComments) {
        //二级节点添加到临时存放集合
        replyComments.add(comment);
        if (comment.getReplyComments().size() > 0) {
            for (Comment reply : comment.getReplyComments()) {
                replyComments.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply, replyComments);
                }
            }
        }
    }

}
