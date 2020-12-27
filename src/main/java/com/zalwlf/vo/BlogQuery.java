package com.zalwlf.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * platform
 * <p>博客查询视图</p>
 *
 * @author : lcq
 * @date : 2020-09-20 16:13
 **/
@Getter
@Setter
public class BlogQuery {
    /**
     * 标题
     */
    private String title;
    /**
     * 分类主键
     */
    private Long typeId;
    /**
     * 推荐
     */
    private boolean recommend;

}
