package com.ProFit.controller.majors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import com.ProFit.bean.MajorBean;
import com.ProFit.bean.UserMajorBean;
import com.ProFit.dao.majorsCRUD.MajorDAO;
import com.ProFit.dao.majorsCRUD.UserMajorDAO;

@WebServlet("/userMajor/*")
public class UserMajorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserMajorDAO userMajorDAO;

    public void init() {
        userMajorDAO = new UserMajorDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/userMajors":
                    listUserMajors(request, response);
                    break;
                case "/majorUsers":
                    listMajorUsers(request, response);
                    break;
                case "/add":
                    addUserMajor(request, response);
                    break;
                case "/delete":
                    deleteUserMajor(request, response);
                    break;
                default:
                    listAllUserMajors(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUserMajors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	 String userName1 = request.getParameter("userName");
         int userId = userMajorDAO.getUserIdByName(userName1);
//        int userId = Integer.parseInt(request.getParameter("userId"));
        List<UserMajorBean> userMajors = userMajorDAO.findMajorsByUserId(userId);
        for (UserMajorBean userMajor : userMajors) {
            String userName = userMajorDAO.getUserNameById(userMajor.getUserId());
            String majorName = userMajorDAO.getMajorNameById(userMajor.getMajorId());
            userMajor.setUserName(userName);
            userMajor.setMajorName(majorName);
        }
        Map<String, String> allMajors = userMajorDAO.getAllMajors();
        Map<String, String> availableMajors = new HashMap<>(allMajors);
        for (UserMajorBean userMajor : userMajors) {
            availableMajors.remove(userMajor.getMajorId());
        }
        request.setAttribute("userMajors", userMajors);
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName1);
        request.setAttribute("availableMajors", availableMajors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/UserMajorList.jsp");
        dispatcher.forward(request, response);
    }

    private void listMajorUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String majorName1 = request.getParameter("majorName");
        int majorId = userMajorDAO.getMajorIdByName(majorName1);
//        int majorId = Integer.parseInt(request.getParameter("majorId"));
        List<UserMajorBean> majorUsers = userMajorDAO.findUsersByMajorId(majorId);
        for (UserMajorBean userMajor : majorUsers) {
            String userName = userMajorDAO.getUserNameById(userMajor.getUserId());
            String majorName = userMajorDAO.getMajorNameById(userMajor.getMajorId());
            userMajor.setUserName(userName);
            userMajor.setMajorName(majorName);
        }
        request.setAttribute("majorUsers", majorUsers);
        request.setAttribute("majorId", majorId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorUserList.jsp");
        dispatcher.forward(request, response);
    }

//    private void listAllUserMajors(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<UserMajorBean> allUserMajors = userMajorDAO.findAllUserMajors();
//        for (UserMajorBean userMajor : allUserMajors) {
//            String userName = userMajorDAO.getUserNameById(userMajor.getUserId());
//            String majorName = userMajorDAO.getMajorNameById(userMajor.getMajorId());
//            userMajor.setUserName(userName);
//            userMajor.setMajorName(majorName);
//        }
//        request.setAttribute("allUserMajors", allUserMajors);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/AllUserMajorList.jsp");
//        dispatcher.forward(request, response);
//    }
    private void listAllUserMajors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<UserMajorBean> allUserMajors = userMajorDAO.findAllUserMajors();
        for (UserMajorBean userMajor : allUserMajors) {
            String userName = userMajorDAO.getUserNameById(userMajor.getUserId());
            String majorName = userMajorDAO.getMajorNameById(userMajor.getMajorId());
            userMajor.setUserName(userName);
            userMajor.setMajorName(majorName);
        }
        request.setAttribute("allUserMajors", allUserMajors);
        
        // 獲取所有用戶和所有專業
        Map<String, String> allUsers = userMajorDAO.getAllUsers();
        Map<String, String> allMajors = userMajorDAO.getAllMajors();
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allMajors", allMajors);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/AllUserMajorList.jsp");
        dispatcher.forward(request, response);
    }

    private void addUserMajor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String majorIdOrName = request.getParameter("majorId");
        int majorId;
        try {
        	majorId = Integer.parseInt(request.getParameter("majorId"));
		} catch (Exception e) {
			// 如果不是數字，假設它是專業名稱，嘗試根據名稱獲取 ID
	        majorId = userMajorDAO.getMajorIdByName(majorIdOrName);
	        if (majorId == -1) {
	            // 處理錯誤：找不到對應的專業
	            response.sendRedirect(request.getContextPath() + "/error.jsp?message=Invalid major");
	            return;
	        }
		}
        UserMajorBean userMajor = new UserMajorBean();
        userMajor.setUserId(userId);
        userMajor.setMajorId(majorId);
        userMajorDAO.insertUserMajor(userMajor);
        response.sendRedirect("userMajors/?userId="+userId);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("userMajors/?userId="+userId);
//        dispatcher.forward(request, response);
    }

    private void deleteUserMajor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int majorId = Integer.parseInt(request.getParameter("majorId"));
        userMajorDAO.deleteUserMajor(userId, majorId);
        response.sendRedirect("userMajor");
    }
}
