package com.zalwlf.service;

import com.zalwlf.po.Type;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * platform
 * <p>分类业务服务规范</p>
 *
 * @author : lcq
 * @date : 2020-09-12 23:30
 **/
public interface TypeService {

    /**
     * 保存分类
     * @param type 分类{@link Type}
     * @return {@link Type}
     */
    Type saveType(Type type);

    /**
     * 通过主键获取分类
     * @param id {@link Long}类型的主键
     * @return {@link Type}
     */
    Type getType(Long id);

    /**
     * 分页查询分类
     * @param pageable 分页条件{@link Pageable}包含当前页,每页条数；可包含排序规则
     * @return {@link Page<Type>}
     */
    Page<Type> listTypePage(Pageable pageable);

    /**
     * 查询所有分类
     * @return {@link List<Type>}
     */
    List<Type> listType();

    /**
     * 修改分类
     * @param id {@link Long}类型的主键
     * @param type 分类{@link Type}
     * @return {@link Page<Type>}
     */
    Type updateType(Long id, Type type);

    /**
     *删除分类
     * @param id {@link Long}类型的主键
     */
    void deleteType(Long id);

    /**
     * 按名称查询
     * @param name {@link String} 分类名称
     * @return {@link Type}
     */
    List<Type> getTypeByName(String name);

    /**
     * 根据字段排序并获取最靠前几项分类信息
     * @param size 总条数
     * @param property 字段
     * @return {@link List<Type>}
     */
    List<Type> listTypeTop(Integer size, String property);
}
