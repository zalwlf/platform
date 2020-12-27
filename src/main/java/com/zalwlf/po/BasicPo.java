package com.zalwlf.po;

import com.alibaba.fastjson.JSON;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * platform
 * <p>基础实体</p>
 *
 * @author : lcq
 * @date : 2020-09-10 21:33
 **/
@Getter
@Setter
@MappedSuperclass
public class BasicPo {
    @Id
    @GeneratedValue
    protected Long id;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
