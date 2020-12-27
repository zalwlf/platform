package com.zalwlf.dao;

import com.zalwlf.po.Type;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * platform
 * <p>分类ORM</p>
 *
 * @author : lcq
 * @date : 2020-09-12 23:48
 **/
public interface TypeRepository extends JpaRepository<Type,Long> {

    /**
     * 通过名称查询
     * @param name {@link String}
     * @return {@link List<Type>}
     */
    List<Type> findByName(String name);

    /**
     * 查询出分类，再按照自定义分页进行处理
     * @param pageable 分页信息{@link Pageable}
     * @return {@link List<Type>}
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
