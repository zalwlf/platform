package com.zalwlf.service;

import com.zalwlf.po.Tag;
import com.zalwlf.po.Type;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * platform
 * <p>标签业务服务规范</p>
 *
 * @author : lcq
 * @date : 2020-09-19 15:05
 **/
public interface TagService {

    /**
     * 保存标签
     * @param tag 标签{@link Tag}
     * @return {@link Tag}
     */
    Tag saveTag(Tag tag);

    /**
     * 通过主键获取标签
     * @param id {@link Long}标签的主键
     * @return {@link Tag}
     */
    Tag getTag(Long id);

    /**
     * 分页查询标签
     * @param pageable 分页条件{@link Pageable}包含当前页,每页条数；可包含排序规则
     * @return {@link Page<Tag>}
     */
    Page<Tag> listTagPage(Pageable pageable);

    /**
     * 修改标签
     * @param id {@link Long}标签的主键
     * @param tag 标签{@link Tag}
     * @return {@link Page<Tag>}
     */
    Tag updateTag(Long id, Tag tag);

    /**
     *删除标签
     * @param id {@link Long}标签的主键
     */
    void deleteTag(Long id);

    /**
     * 按名称查询
     * @param name {@link String} 标签名称
     * @return {@link Tag}
     */
    List<Tag> getTagByName(String name);

    /**
     * 查询所有标签
     * @return {@link List<Tag>}
     */
    List<Tag> listTag();

    /**
     * 根据逗号间隔字符串主键查询
     * @param idsOrNames {@link String} 如："1,2,3,4"
     * @return {@link List<Tag>}
     */
    List<Tag> listTag(String idsOrNames);

    /**
     * 用于即时自定义新增若干标签
     * <p>标签间需要用逗号隔开,标签名如果为阿拉伯数字则不生效</p>
     * @param names {@link String}标签名(一到多个)
     */
    void saveTagOnlyNames(String names);

    /**
     * 根据字段排序并获取最靠前几项标签信息
     * @param size 总条数
     * @param property 字段
     * @return {@link List<Tag>}
     */
    List<Tag> listTagTop(Integer size, String property);

}
