package com.zalwlf.service;

import com.zalwlf.po.Blog;
import com.zalwlf.vo.BlogQuery;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * platform
 * <p>博客业务服务规范</p>
 *
 * @author : lcq
 * @date : 2020-09-19 17:19
 **/
public interface BlogService {
    /**
     * 通过主键获取博客信息
     * @param id {@link Long} 主键
     * @return {@link Blog}
     */
    Blog getBlog(Long id);

    /**
     * 分页查询博客列表
     * @param pageable {@link Pageable}
     * @param blog {@link BlogQuery} 博客搜索条件
     * @return {@link Page<Blog>}
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 新增博客
     * @param blog {@link Blog} 新增博客内容
     * @return {@link Blog}
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改博客内容
     * @param id {@link Long} 博客主键
     * @param blog {@link Blog} 博客内容
     * @return {@link Blog}
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 删除博客内容
     * @param id {@link Long} 博客主键
     */
    void deleteBlog(Long id);

    /**
     * 排序查找最新合适的8条数据
     * @return {@link List<Blog>}
     */
    List<Blog> findTop8ByOrderByUpdateTimeDesc();

    /**
     * 排序查找最新合适的3条数据
     * @return {@link List<Blog>}
     */
    List<Blog> findTop3ByOrderByUpdateTimeDesc();

    /**
     * 搜索
     * @param query 搜索内容{@link String}
     * @param pageable 分页信息 {@link Pageable}
     * @return {@link Page<Blog>}
     */
    Page<Blog> listBlog(String query, Pageable pageable);

    /**
     * 浏览次数记录
     * @param id 博客主键{@link Long}
     */
    void updateBlogViews(Long id);

    /**
     * 根据标签查询所有博客信息
     * @param pageable 分页信息 {@link Pageable}
     * @param tagId 标签主键{@link Long}
     * @return {@link Page<Blog>}
     */
    Page<Blog> listBlog(Pageable pageable, Long tagId);

    /**
     * 按年分类查找博客
     * @return {@link Map}
     */
    Map<String,List<Blog>> listBlogByYear();

    /**
     * 查询博客总数
     * @return {@link Long}
     */
    Long countBlog();
}
