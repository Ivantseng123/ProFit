package com.ProFit.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.ProFit.controller.interceptor.LoginInterceptor;



//相當於mvc-servlet.xml的Java程式組態
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ProFit.controller" })

public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver irViewResolver() {
		InternalResourceViewResolver irv1 = new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
		irv1.setOrder(6);
		return irv1;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Autowired
//	private LoginInterceptor loginInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(loginInterceptor).addPathPatterns("/**") // 過濾所有請求
//				.excludePathPatterns("/loginpage","/login"); // 排除登入頁面
//	}
}