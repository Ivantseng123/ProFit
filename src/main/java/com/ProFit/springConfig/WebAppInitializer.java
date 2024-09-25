package com.ProFit.springConfig;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;

// 相當於 web.xml 的 Java 程式組態
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    // 設定註冊相當於 beans.config.xml 的 Java 程式組態類別
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ApplicationConfig.class};
        // return null;
    }

    @Override
    // 設定註冊相當於 mvc-servlet.xml 的 Java 程式組態類別
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebAppConfig.class};
        // return null;
    }

    @Override
    // 設定 DispatcherServlet 的 URL 映射模式
    protected String[] getServletMappings() {
        return new String[] {"/"};
        // return null;
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter cef = new CharacterEncodingFilter("UTF-8", true);
        return new Filter[] {cef};
    }
}