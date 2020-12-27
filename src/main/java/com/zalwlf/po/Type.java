package com.zalwlf.po;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * platform
 * <p>分类</p>
 *
 * @author : lcq
 * @date : 2020-09-10 21:31
 **/
@Getter
@Setter
@Entity
@Table(name = "t_type")
public class Type extends BasicPo {
    /**
     * 类型名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>(0);

}
