//package com.ProFit.controller.courses;
//
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//import java.io.IOException;
//
//@WebListener
//public class ServerStartupListener implements ServletContextListener {
//
//    @SuppressWarnings("deprecation")
//	@Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            // 設置要打開的 URL
//            String url = "http://localhost:8080/ProFit/usersVIEW/Login.jsp";
//
//            // 根據操作系統來打開瀏覽器
//            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//                Runtime.getRuntime().exec("open " + url);  // Mac 系統
//            } else if (System.getProperty("os.name").toLowerCase().contains("win")) {
//                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);  // Windows 系統
//            } else {
//                Runtime.getRuntime().exec("xdg-open " + url);  // Linux 系統
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        // 伺服器關閉時的操作（可選）
//    }
//}