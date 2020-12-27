package com.zalwlf.common.interceptor;

import com.zalwlf.common.constant.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * platform
 * <p>登录拦截器</p>
 *
 * @author : lcq
 * @date : 2020-09-12 20:11
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){
            response.sendRedirect(Constants.PLATFORM_BLOG_V_PATH_P +"/admin/user/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
