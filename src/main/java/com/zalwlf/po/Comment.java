package com.zalwlf.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zalwlf.common.util.Verify;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * platform
 * <p>评论</p>
 *
 * @author : lcq
 * @date : 2020-09-10 21:50
 **/
@Getter
@Setter
@Entity
@Table(name = "t_comment")
public class Comment extends BasicPo implements EntityInitializer {
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 是否博主评论
     */
    private boolean blogger;

    @JsonIgnore
    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    private Comment parentComment;

    @Override
    public void createDefaultNewItem() {
        this.createTime = new Date();
        if (Verify.isBlank(this.avatar)){
            this.avatar = "/images/avatar.png";
        }
    }

    @Override
    public void createDefaultModification() {
    }

    @Override
    public void loadDefaultViewData() {
    }
}
