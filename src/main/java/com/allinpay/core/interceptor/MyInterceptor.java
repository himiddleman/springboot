package com.allinpay.core.interceptor;

import com.allinpay.core.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //方法处理前打印接口信息
        Method method = ((HandlerMethod) o).getMethod();
        boolean annotationPresent = method.isAnnotationPresent(MyAnnotation.class);
        if (annotationPresent) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            log.info(annotation.name());
        }
        String url = httpServletRequest.getRequestURI();
        if ("/error".equals(url)) {
            log.warn("未找到指定资源{}", url);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
