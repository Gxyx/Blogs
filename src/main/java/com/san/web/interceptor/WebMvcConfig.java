package com.san.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/15:59
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    // 重写addInterceptors()方法，注册自定义拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }
}
