package com.example.tododevelopproject.config;

import com.example.tododevelopproject.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean LoginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(); // 필터를 직접 등록, 설정하기 위해 필요
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*"); // 추후 새로운 URL Mapping이 생기면 수정해야 하므로 전체 경로로 설정

        return filterRegistrationBean;
    }
}
