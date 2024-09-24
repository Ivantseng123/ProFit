//package com.ProFit.controller.majors;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.ProFit.bean.majorsBean.UserMajorBean;
//import com.ProFit.bean.majorsBean.UserMajorPK;
//import com.ProFit.bean.majorsBean.MajorBean;
//import com.ProFit.bean.usersBean.Users;
//import com.ProFit.dao.majorsDao.HUserMajorDAO;
//import com.ProFit.util.hibernateutil.HibernateUtil;
//
//import org.hibernate.Session;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/userMajor/*")
//public class UserMajorServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getPathInfo();
//
//        try {
//            switch (action) {
//                case "/userMajors":
//                    listUserMajors(request, response);
//                    break;
//                case "/majorUsers":
//                    listMajorUsers(request, response);
//                    break;
//                case "/add":
//                    addUserMajor(request, response);
//                    break;
//                case "/delete":
//                    deleteUserMajor(request, response);
//                    break;
//                default:
//                    listAllUserMajors(request, response);
//                    break;
//            }
//        } catch (Exception ex) {
//            throw new ServletException(ex);
//        }
//    }
//
//    // 列出特定用戶的所有專業
//    private void listUserMajors(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        String userIdStr = request.getParameter("userId");
//        
//        if (userIdStr == null) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
//            return;
//        }      
//        int userId = Integer.parseInt(userIdStr);
//        
//        
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);
//       
//        Users user = session.get(Users.class, userId);
//        if (user == null) {
//            session.getTransaction().rollback();
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
//            return;
//        }
//        
//
//        List<UserMajorBean> userMajors = userMajorDAO.findMajorsByUserId(user.getUserId());
//        Map<Integer, String> allMajors = userMajorDAO.getAllMajors();
//        Map<Integer, String> availableMajors = new HashMap<>(allMajors);
//
//        
//        for (UserMajorBean userMajor : userMajors) {
//            availableMajors.remove(userMajor.getId().getMajor().getMajorId());
//        }
//
//        request.setAttribute("userMajors", userMajors);
//        request.setAttribute("userId", userId);
//        request.setAttribute("userName", user.getUserName());
//        request.setAttribute("availableMajors", availableMajors);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/UserMajorList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 列出特定專業的所有用戶
//    private void listMajorUsers(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String majorIdStr = request.getParameter("majorId");
//        
//        if (majorIdStr == null) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid major ID");
//            return;
//        }      
//        int majorId = Integer.parseInt(majorIdStr);
//        
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);
//
//        MajorBean major = session.get(MajorBean.class, majorId);
//        if (major == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Major not found");
//            return;
//        }
//
//        List<UserMajorBean> majorUsers = userMajorDAO.findUsersByMajorId(major.getMajorId());
//        request.setAttribute("majorUsers", majorUsers);
//        request.setAttribute("majorId", majorId);
//        request.setAttribute("majorName", major.getMajorName());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorUserList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 列出所有用戶-專業關聯
//    private void listAllUserMajors(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);
//
//        List<UserMajorBean> allUserMajors = userMajorDAO.findAllUserMajors();
//        Map<Integer, String> allUsers = userMajorDAO.getAllUsers();
//        Map<Integer, String> allMajors = userMajorDAO.getAllMajors();
//
//        request.setAttribute("allUserMajors", allUserMajors);
//        request.setAttribute("allUsers", allUsers);
//        request.setAttribute("allMajors", allMajors);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/AllUserMajorList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 為用戶添加新專業
//    private void addUserMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        int majorId =Integer.parseInt(request.getParameter("majorId"));
//        //System.out.println(request.getParameter("majorId"));
//        
//
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);
//
//        Users user = session.get(Users.class, userId);
//        MajorBean major = session.get(MajorBean.class, majorId);
//        
//        if (user == null || major == null) {
//        	System.out.println("user or major is not exist");
//        	return;
//		}
//        
//        UserMajorPK id = new UserMajorPK(user, major);
//        UserMajorBean userMajor = new UserMajorBean(id);
//        userMajorDAO.insertUserMajor(userMajor);
//        
//        response.sendRedirect(request.getContextPath() + "/userMajor/userMajors?userId=" + userId);
//        
//    }
//
//    // 刪除用戶-專業關聯
//    private void deleteUserMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        int majorId = Integer.parseInt(request.getParameter("majorId"));
//
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);
//
//        userMajorDAO.deleteUserMajor(userId, majorId);
//
//        response.sendRedirect(request.getContextPath() + "/userMajor/");
//    }
//}