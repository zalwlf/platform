package com.zalwlf.service.impl;

import com.zalwlf.common.exception.NotFoundException;
import com.zalwlf.dao.TypeRepository;
import com.zalwlf.po.Type;
import com.zalwlf.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(new Type());
    }

    @Override
    public Page<Type> listTypePage(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type updateType(Long id, Type type) {
        Type t = getType(id);
        if (t == null){
            throw new NotFoundException("分类不存在");
        }
        BeanUtils.copyProperties(type, t, PLATFORM_BLOG_V_FIELD_ID);
        return typeRepository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public List<Type> getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listTypeTop(Integer size, String property) {
        return typeRepository.findTop(PageRequest.of(0, size, Sort.Direction.DESC, property));
    }
}
