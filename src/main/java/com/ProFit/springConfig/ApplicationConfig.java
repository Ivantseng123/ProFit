package com.ProFit.springConfig;

   import org.springframework.context.annotation.Configuration;
   import org.springframework.context.annotation.ComponentScan;

   @Configuration
   @ComponentScan(basePackages = "com.ProFit")
   public class ApplicationConfig {
       // 您可以在這裡添加@Bean方法來定義beans
   }
   