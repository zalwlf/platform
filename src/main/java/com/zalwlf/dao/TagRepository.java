package com.zalwlf.dao;

import com.zalwlf.po.Tag;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * platform
 * <p>标签ORM</p>
 *
 * @author : lcq
 * @date : 2020-09-19 17:26
 **/
public interface TagRepository extends JpaRepository<Tag,Long> {

    /**
     * 通过名称查询
     * @param name {@link String}
     * @return {@link List<Tag>}
     */
    List<Tag> findByName(String name);

    /**
     * 查询出标签，再按照自定义分页进行处理
     * @param pageable 分页信息{@link Pageable}
     * @return {@link List<Tag>}
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
