package com.ProFit.controller.majors;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.majorsCRUD.HUserMajorDAO;
import com.ProFit.hibernateutil.HibernateUtil;

import org.hibernate.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userMajor/*")
public class UserMajorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
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
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listUserMajors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userName = request.getParameter("userName");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);

        Users user = session.createQuery("FROM Users WHERE userName = :userName", Users.class)
                            .setParameter("userName", userName)
                            .uniqueResult();
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        List<UserMajorBean> userMajors = userMajorDAO.findMajorsByUserId(user.getUserId());
        Map<String, String> allMajors = userMajorDAO.getAllMajors();
        Map<String, String> availableMajors = new HashMap<>(allMajors);

        for (UserMajorBean userMajor : userMajors) {
            availableMajors.remove(userMajor.getId().getMajor().getMajorName());
        }

        request.setAttribute("userMajors", userMajors);
        request.setAttribute("userId", user.getUserId());
        request.setAttribute("userName", userName);
        request.setAttribute("availableMajors", availableMajors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/UserMajorList.jsp");
        dispatcher.forward(request, response);
    }

    private void listMajorUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String majorName = request.getParameter("majorName");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);

        MajorBean major = session.createQuery("FROM MajorBean WHERE majorName = :majorName", MajorBean.class)
                                 .setParameter("majorName", majorName)
                                 .uniqueResult();
        if (major == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Major not found");
            return;
        }

        List<UserMajorBean> majorUsers = userMajorDAO.findUsersByMajorId(major.getMajorId());
        request.setAttribute("majorUsers", majorUsers);
        request.setAttribute("majorId", major.getMajorId());
        request.setAttribute("majorName", majorName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorUserList.jsp");
        dispatcher.forward(request, response);
    }

    private void listAllUserMajors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);

        List<UserMajorBean> allUserMajors = userMajorDAO.findAllUserMajors();
        Map<String, String> allUsers = userMajorDAO.getAllUsers();
        Map<String, String> allMajors = userMajorDAO.getAllMajors();

        request.setAttribute("allUserMajors", allUserMajors);
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allMajors", allMajors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/AllUserMajorList.jsp");
        dispatcher.forward(request, response);
    }

    private void addUserMajor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String majorName = request.getParameter("majorName");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);

        Users user = session.createQuery("FROM Users WHERE userName = :userName", Users.class)
                            .setParameter("userName", userName)
                            .uniqueResult();
        MajorBean major = session.createQuery("FROM MajorBean WHERE majorName = :majorName", MajorBean.class)
                                 .setParameter("majorName", majorName)
                                 .uniqueResult();

        if (user == null || major == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user or major");
            return;
        }

        UserMajorPK id = new UserMajorPK(user, major);
        UserMajorBean userMajor = new UserMajorBean(id);
        userMajorDAO.insertUserMajor(userMajor);

        response.sendRedirect(request.getContextPath() + "/userMajor/userMajors?userName=" + userName);
    }

    private void deleteUserMajor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int majorId = Integer.parseInt(request.getParameter("majorId"));

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HUserMajorDAO userMajorDAO = new HUserMajorDAO(session);

        userMajorDAO.deleteUserMajor(userId, majorId);

        response.sendRedirect(request.getContextPath() + "/userMajor");
    }
}