package com.allinpay.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
/**
 * @description: 主要针对于url的拦截，session超时处理，权限控制
 * @author: tanguang
 * @date: 2019-07-09
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    private static List<String> uncheckedUrlList = new ArrayList<>();

    static {
        uncheckedUrlList.add("/sys/login");
        uncheckedUrlList.add("/sys/captcha");
        uncheckedUrlList.add("/error");
        uncheckedUrlList.add("/web/login");
        uncheckedUrlList.add("/web/index");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        String url = request.getRequestURI();
//        //登录、获取验证码直接放行; 获取服务器图片信息直接放行（会是/error）
//        if (uncheckedUrlList.contains(url)) {
//            return true;
//        }
//
//        //ajax请求 session超时处理
//        if (!SecurityUtils.getSubject().isAuthenticated()) {
//            if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
//                //在ajax响应头部设置一个sessionstatus状态，用于在ajax全局js(ajaxcommon.js)中判断
//                log.info("用户登录超时！");
//                response.setHeader("sessionStatus", "timeout");
//                response.getWriter().print("timeout");
//                return false;
//            }
//        }
//        //做权限拦截 url处理
//        UserVO userVO = (UserVO) SecurityUtils.getSubject().getPrincipal();
//        if (Objects.nonNull(userVO) && !userVO.getUrlList().contains(url)) {
//            log.warn("用户【{}】没有权限访问：{}", userVO.getEmail(), url);
//            if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
//                response.setHeader("authorityStatus", "forbidden");
//                response.getWriter().print("forbidden");
//            } else {
//                throw new UnauthorizedException();
//            }
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
