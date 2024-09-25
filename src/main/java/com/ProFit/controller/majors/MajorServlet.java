//package com.ProFit.controller.majors;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.ProFit.bean.majorsBean.MajorBean;
//import com.ProFit.dao.majorsDao.HMajorDAO;
//import com.ProFit.dao.majorsDao.IHMajorDAO;
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
//@WebServlet("/major/*")
//public class MajorServlet extends HttpServlet {
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
//                case "/new":
//                    showNewForm(request, response);
//                    break;
//                case "/insert":
//                    insertMajor(request, response);
//                    break;
//                case "/delete":
//                    deleteMajor(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateMajor(request, response);
//                    break;
//                case "/view":
//                    viewMajor(request, response);
//                    break;
//                case "/category":
//                    listMajorsByCategory(request, response);
//                    break;
//                default:
//                    listMajor(request, response);
//                    break;
//            }
//        } catch (Exception ex) {
//            throw new ServletException(ex);
//        }
//    }
//
//    // 列出所有專業
//    private void listMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        List<MajorBean> listMajor = majorDAO.findAllMajors();
//        
//        request.setAttribute("listMajor", listMajor);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 顯示新增專業表單
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 插入新專業
//    private void insertMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String idStr = request.getParameter("majorId");
//        String name = request.getParameter("majorName");
//        String categoryIdStr = request.getParameter("majorCategoryId");
//        String description = request.getParameter("majorDescription");
//
//        MajorBean newMajor = new MajorBean();
//
//        if (idStr != null && !idStr.trim().isEmpty()) {
//            try {
//                int id = Integer.parseInt(idStr);
//                newMajor.setMajorId(id);
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "無效的Major ID, 請輸入有效數字");
//                request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
//                return;
//            }
//        } else {
//            request.setAttribute("error", "需填寫Major ID, 請輸入有效數字");
//            request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
//            return;
//        }
//
//        newMajor.setMajorName(name);
//
//        if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
//            try {
//                int categoryId = Integer.parseInt(categoryIdStr);
//                newMajor.setMajorCategoryId(categoryId);
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "無效的Category ID, 請輸入有效數字");
//                request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
//                return;
//            }
//        } else {
//            request.setAttribute("error", "需填寫Category ID, 請輸入有效數字");
//            request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
//            return;
//        }
//
//        newMajor.setMajorDescription(description);
//
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        majorDAO.insertMajor(newMajor);
//        response.sendRedirect("list");
//    }
//
//    // 顯示編輯專業表單
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        MajorBean existingMajor = majorDAO.findMajorById(id);
//        
//        request.setAttribute("major", existingMajor);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 更新專業
//    private void updateMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("majorId"));
//        String name = request.getParameter("majorName");
//        int categoryId = Integer.parseInt(request.getParameter("majorCategoryId"));
//        String description = request.getParameter("majorDescription");
//
//        MajorBean major = new MajorBean(id, name, categoryId, description);
//
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        majorDAO.updateMajor(major);
//        response.sendRedirect("list");
//    }
//
//    // 刪除專業
//    private void deleteMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        majorDAO.deleteMajor(id);
//        response.sendRedirect("list");
//    }
//
//    // 查看專業詳情
//    private void viewMajor(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        IHMajorDAO majorDAO = new HMajorDAO(session);
//        MajorBean major = majorDAO.findMajorById(id);
//        
//        request.setAttribute("major", major);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorView.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // 列出特定類別下的所有專業
//    private void listMajorsByCategory(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String categoryIdStr = request.getParameter("categoryId");
//        if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
//            try {
//                int categoryId = Integer.parseInt(categoryIdStr);
//                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//                IHMajorDAO majorDAO = new HMajorDAO(session);
//                List<MajorBean> majors = majorDAO.findMajorsByCategoryId(categoryId);
//                
//                request.setAttribute("listMajor", majors);
//                request.setAttribute("categoryId", categoryId);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
//                dispatcher.forward(request, response);
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "Invalid category ID. Please provide a valid number.");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
//                dispatcher.forward(request, response);
//            }
//        } else {
//            request.setAttribute("error", "Category ID is required.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
//            dispatcher.forward(request, response);
//        }
//    }
//}