package com.ProFit.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.HcourseDao;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.hibernateutil.JsonUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();

    // 使用不同的格式器來解析日期和時間
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CreateServlet() {
        super();
    }

    // 處理所有新增的邏輯
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseName = request.getParameter("courseName");
        String courseCategory = request.getParameter("courseCategory");
        String courseCreateUserId = request.getParameter("courseCreateUserId");
        String courseInformation = request.getParameter("courseInformation");
        String courseDescription = request.getParameter("courseDescription");

        // 獲取日期並轉換成 LocalDate
        String enrollmentDateString = request.getParameter("courseEnrollmentDate");
        LocalDate courseEnrollmentDate = enrollmentDateString != null ? LocalDate.parse(enrollmentDateString, dateFormatter) : null;

        // 獲取日期時間並轉換成 LocalDateTime
        String startDateString = request.getParameter("courseStartDate");
        LocalDateTime courseStartDate = startDateString != null ? LocalDateTime.parse(startDateString, dateTimeFormatter) : null;

        String endDateString = request.getParameter("courseEndDate");
        LocalDateTime courseEndDate = endDateString != null ? LocalDateTime.parse(endDateString, dateTimeFormatter) : null;

        String coursePrice = request.getParameter("coursePrice");
        String courseStatus = request.getParameter("courseStatus");

        CourseBean courseBean = new CourseBean(courseName, courseCreateUserId, courseCategory, courseInformation, courseDescription,
                courseEnrollmentDate, courseStartDate, courseEndDate, coursePrice, courseStatus);

        HcourseDao hcourseDao = new HcourseDao(session);

        // 新增課程後回傳是否成功
        CourseBean newCourse = hcourseDao.insertCourse(courseBean);

        // 構建 JSON 響應
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("success", newCourse != null);

        if (newCourse != null) {
            jsonResponse.put("newCourse", courseBean);
        }

        // 使用 Jackson 進行序列化
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 使用 JsonUtil 將響應對象轉換成 JSON 字符串
        String json = JsonUtil.toJson(jsonResponse);

        // 打印和發送 JSON 響應
        System.out.println("Serialized JSON: " + json);
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}