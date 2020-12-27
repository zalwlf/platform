package com.zalwlf.dao;

import com.zalwlf.po.Blog;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * platform
 * <p>博客ORM</p>
 *
 * @author : lcq
 * @date : 2020-09-12 23:48
 **/
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    /**
     * 查询最新八个博客
     * @return {@link List<Blog>}
     */
    List<Blog> findTop8ByOrderByUpdateTimeDesc();

    /**
     * 博客模糊搜索,通过标题, 内容或描述
     * @param query 搜索内容,需要添加标识符，如'%abc%'{@link String}
     * @param pageable 分页信息 {@link Pageable}
     * @return {@link Page<Blog>}
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1 or b.description like ?1 ")
    Page<Blog> findByTitleOrDescriptionOrContentLike(String query, Pageable pageable);

    /**
     * 记录浏览次数
     * @param id 博客主键{@link Long}
     * @return 更新的数据数量
     */
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateBlogViews(Long id);

    /**
     * 查询博客创建年份
     * @return {@link List<String>}
     */
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc")
    List<String> findYearsGroup();

    /**
     * 按年份查找博客
     * @param year {@link String}
     * @return {@link List<Blog>}
     */
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') =?1")
    List<Blog> findByYear(String year);

    /**
     * 查询最新三个博客
     * @return {@link List<Blog>}
     */
    List<Blog> findTop3ByOrderByUpdateTimeDesc();
}
