package com.zalwlf.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * platform
 * <p>用户</p>
 *
 * @author : lcq
 * @date : 2020-09-10 22:01
 **/
@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User extends BasicPo {
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>(0);


}
