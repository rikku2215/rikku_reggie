package com.rikku.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.rikku.reggie.common.BaseContext;
import com.rikku.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String[] uris = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",  //移动端发送短信
                "/user/login"   //移动端登录
        };

        //log.info("过滤器：本次访问页面为"+requestURI);

        boolean check = check(uris,requestURI);

        if (check){
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute("employee")!=null){
            //当前线程的id
            Long id = Thread.currentThread().getId();
            //当前操作用户id
            Long empId = (Long) request.getSession().getAttribute("employee");
            //设置当前操作用户id于ThreadLocal中
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }
        if (request.getSession().getAttribute("user")!=null){
            //当前线程的id
            Long id = Thread.currentThread().getId();
            //当前操作用户id
            Long userId = (Long) request.getSession().getAttribute("user");
            //设置当前操作用户id于ThreadLocal中
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }


        //未登录，通过输出流方式向客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }


    public boolean check(String[] urls,String requestRUI){
        for (String url : urls){
            boolean match = PATH_MATCHER.match(url,requestRUI);
            if (match){
                return true;
            }
        }
        return false;
    }

}
