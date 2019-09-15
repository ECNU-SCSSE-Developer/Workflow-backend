package com.scsse.workflow.config;

import com.scsse.workflow.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-15 09:24
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    /**
     * 对拦截器进行Bean处理, 否则拦截器中无法注入Bean
     * @return
     */
    @Bean
    public LoginInterceptor myInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 注册拦截器myInterceptor
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}