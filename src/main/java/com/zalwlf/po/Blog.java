package com.zalwlf.po;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.util.MarkDownUtil;
import com.zalwlf.common.util.Verify;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * platform
 * <p>博客</p>
 *
 * @author : lcq
 * @date : 2020-09-10 21:19
 **/
@Getter
@Setter
@Entity
@Table(name = "t_blog")
public class Blog extends BasicPo implements EntityInitializer {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = false, columnDefinition = "Text")
    private String content;
    /**
     * 首图
     */
    private String firstPicture;
    /**
     * 标记
     */
    private String flag;
    /**
     * 浏览次数
     */
    private Integer views;
    /**
     * 开启赞赏
     */
    private boolean appreciation;
    /**
     * 转载声明
     */
    private boolean shareStatement;
    /**
     * 允许评论
     */
    private boolean commentTabled;
    /**
     * 是否发布
     */
    private boolean published;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 博客描述
     */
    private String description;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>(0);

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>(0);

    @Transient
    private String tagIds;

    @Transient
    private String htmlContent;

    @Override
    public void createDefaultNewItem() {
        if (this.createTime == null){
            this.createTime = new Date();
        }
        if (this.updateTime == null){
            this.updateTime = new Date();
        }
        if (this.views == null){
            this.views = 0;
        }
    }

    @Override
    public void createDefaultModification() {
        this.updateTime = new Date();
    }

    @Override
    public void loadDefaultViewData() {
        if (tags != null && tags.size() > 0){
            StringBuilder tagIds = new StringBuilder();
            tags.forEach(tag -> tagIds.append(tag.id).append(Constants.PLATFORM_BLOG_V_CHAR_SPLIT));
            this.tagIds = tagIds.substring(0,tagIds.length() - 1);
        }
        convertMarkdownToHtml();
    }

    public void convertMarkdownToHtml(){
        if (Verify.isNotBlank(this.content)){
            this.htmlContent = MarkDownUtil.markdownToHtmlExtensions(this.content);
        }
    }
}
