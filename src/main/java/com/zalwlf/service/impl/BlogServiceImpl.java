package com.zalwlf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.exception.NotFoundException;
import com.zalwlf.dao.BlogRepository;
import com.zalwlf.po.Blog;
import com.zalwlf.po.Type;
import com.zalwlf.service.BlogService;
import com.zalwlf.vo.BlogQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * platform
 * <p>博客业务服务</p>
 *
 * @author : lcq
 * @date : 2020-09-19 17:26
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElse(new Blog());
        blog.loadDefaultViewData();
        return blog;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        Page<Blog> all = blogRepository.findAll((Specification<Blog>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + blog.getTitle() + "%"));
            }
            if (blog.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }
            if (blog.isRecommend()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
        all.getContent().forEach(Blog::loadDefaultViewData);
        return all;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Blog saveBlog(Blog blog) {
        blog.createDefaultNewItem();
        return blogRepository.save(blog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = getBlog(id);
        if (b == null || b.getId() == null){
            throw new NotFoundException("博客不存在");
        }
        BeanUtil.copyProperties(blog, b, CopyOptions.create()
                .setIgnoreNullValue(true)
                .setIgnoreError(true)
                .setIgnoreProperties(Constants.PLATFORM_BLOG_V_FIELD_ID));
        b.createDefaultModification();
        return blogRepository.save(b);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> findTop8ByOrderByUpdateTimeDesc() {
        return blogRepository.findTop8ByOrderByUpdateTimeDesc();
    }

    @Override
    public List<Blog> findTop3ByOrderByUpdateTimeDesc() {
        return blogRepository.findTop3ByOrderByUpdateTimeDesc();
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByTitleOrDescriptionOrContentLike(query, pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlogViews(Long id) {
        int views = blogRepository.updateBlogViews(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogRepository.findAll((root, query, criteriaBuilder) ->{
            Join<Object, Object> join = root.join("tags");
            return criteriaBuilder.equal(join.get("id"), tagId);
        }, pageable);
    }

    @Override
    public Map<String, List<Blog>> listBlogByYear() {
        List<String> years = blogRepository.findYearsGroup();
        Map<String,List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }
}
