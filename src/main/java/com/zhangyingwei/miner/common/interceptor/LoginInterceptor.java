package com.zhangyingwei.miner.common.interceptor;

import com.zhangyingwei.miner.common.Auth;
import com.zhangyingwei.miner.controller.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by zhangyw on 2017/7/14.
 * 登录拦截
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
            return this.doHandle(request, response, handler);
        }
        return true;
    }

    private boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        Object oldToken = session.getAttribute("miner-token");
        String uri = request.getRequestURI();
        String token = request.getHeader("miner-token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("miner-token");
        }
        logger.info(oldToken+"");
        logger.info(token+"");

        if (null != oldToken && oldToken.equals(token)) {
//            logger.info("已经登录过了");
        } else {
            logger.info("没有登录");
            response.getWriter().write(Result.nologin());
            return false;
        }
        return true;
    }
}
