package com.ProFit.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// 相當於 mvc-servlet.xml 的 Java 程式組態
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ProFit.controller"})
public class WebAppConfig implements WebMvcConfigurer {
    
    // 配置 InternalResourceViewResolver，用於解析視圖名稱到具體的 JSP 文件
    @Bean
    public InternalResourceViewResolver irViewResolver() {
        InternalResourceViewResolver irv1 = new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
        irv1.setOrder(6);
        return irv1;
    }

    // 配置 DefaultServletHandler，用於處理靜態資源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
}