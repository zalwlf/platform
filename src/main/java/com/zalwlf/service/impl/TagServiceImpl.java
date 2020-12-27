package com.zalwlf.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zalwlf.common.exception.NotFoundException;
import com.zalwlf.common.util.CollectionsUtil;
import com.zalwlf.common.util.Verify;
import com.zalwlf.dao.TagRepository;
import com.zalwlf.po.Tag;
import com.zalwlf.service.TagService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.zalwlf.common.constant.Constants.PLATFORM_BLOG_V_FIELD_ID;

/**
 * platform
 * <p>分类业务服务</p>
 *
 * @author : lcq
 * @date : 2020-09-12 23:45
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(new Tag());
    }

    @Override
    public Page<Tag> listTagPage(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = getTag(id);
        if (t == null || t.getId() == null){
            throw new NotFoundException("标签不存在");
        }
        BeanUtils.copyProperties(tag, t, PLATFORM_BLOG_V_FIELD_ID);
        return tagRepository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String idsOrNames) {
        List<Long> list = new ArrayList<>(0);
        if (StrUtil.isNotEmpty(idsOrNames)){
            for (String idOrName : CollectionsUtil.stringToListSplit(idsOrNames)) {
                if (Verify.isNotDigit(idOrName)){
                    List<Tag> tagByName = getTagByName(idOrName);
                    if (Verify.isNotEmpty(tagByName)){
                        list.add(tagByName.get(0).getId());
                    }
                    continue;
                }
                list.add(new Long(idOrName));
            }
        }
        return tagRepository.findAllById(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTagOnlyNames(String names) {
        List<Tag> tags = new ArrayList<>(0);
        if (StrUtil.isNotEmpty(names)){
            for (String name : CollectionsUtil.stringToListSplit(names)) {
                if(Verify.isDigit(name)) {
                    continue;
                }
                tags.add(saveTag(new Tag(name)));
            }
        }
    }

    @Override
    public List<Tag> listTagTop(Integer size, String property) {
        return tagRepository.findTop(PageRequest.of(0, size, Sort.Direction.DESC, property));
    }
}
