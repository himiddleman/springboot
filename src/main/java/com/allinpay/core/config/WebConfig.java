package com.allinpay.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.allinpay.core.interceptor.AuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorityInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问js/css/img/file等静态资源 pathPatterns指的是url路径规则
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //访问html资源，一般是设置进制访问的
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
    }

    /**
     * 配置自定义消息转换器
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //设置日期的返回格式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //空字符串处理
        config.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        fjc.setFastJsonConfig(config);
        converters.add(fjc);
    }
}
