package com.water.quality.filter;

import com.water.quality.context.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录拦截器
 * @author zby
 * @date 2022-02-15 01:26
 */
@Component
public class UserTokenFilter implements Filter {
    @Override
    /**
     * 设置token
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader("token");
        if (StringUtils.isEmpty(token)) {
            System.out.println("用户未登录");
        } else {
            UserContext.setToken(token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
