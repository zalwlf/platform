package com.zalwlf.common.config;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * platform
 * <p>拦截器注册器</p>
 * 扩展SpringMVC
 * SpringBoot2使用的Spring5，因此将WebMvcConfigurerAdapter改为WebMvcConfigurer
 * 使用WebMvcConfigurer扩展SpringMVC好处既保留了SpringBoot的自动配置，又能用到我们自己的配置
 * 如果我们需要全面接管SpringBoot中的SpringMVC配置则开启此注解{@link EnableWebMvc}
 * 开启后，SpringMVC的自动配置将会失效。
 * @author : lcq
 * @date : 2020-09-12 20:42
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/**")
                .excludePathPatterns(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user/login")
                .excludePathPatterns(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user/log/in");
    }
}
