package com.ProFit.controller.majors;

import java.io.IOException;
import java.util.List;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.MajorCategoryBean;
import com.ProFit.dao.majorsCRUD.HmajorCategoryDAO;
import com.ProFit.dao.majorsCRUD.HMajorDAO;
import com.ProFit.hibernateutil.HibernateUtil;

import org.hibernate.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/majorCategory/*")
public class MajorCategoryServlet extends HttpServlet {
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
                case "/list":
                    listMajorCategories(request, response);
                    break;
                case "/majors":
                    listMajorsByCategory(request, response);
                    break;
                case "/insert":
                    insertMajorCategory(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/delete":
                    deleteMajorCategory(request, response);
                    break;
                case "/edit":
                    updateMajorCategory(request, response);
                    break;
                default:
                    listMajorCategories(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // 列出所有專業類別
    private void listMajorCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HmajorCategoryDAO majorCategoryDAO = new HmajorCategoryDAO(session);
        List<MajorCategoryBean> listMajorCategory = majorCategoryDAO.findAllMajorCategories();
        request.setAttribute("listMajorCategory", listMajorCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryList.jsp");
        dispatcher.forward(request, response);
    }

    // 列出特定類別下的所有專業	
    private void listMajorsByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HMajorDAO majorDAO = new HMajorDAO(session);
        HmajorCategoryDAO majorCategoryDAO = new HmajorCategoryDAO(session);
        List<MajorBean> listMajor = majorDAO.findMajorsByCategoryId(categoryId);
        MajorCategoryBean category = majorCategoryDAO.findMajorCategoryById(categoryId);

        request.setAttribute("listMajor", listMajor);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorListByCategory.jsp");
        dispatcher.forward(request, response);
    }

    // 顯示新增專業類別表單
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
        dispatcher.forward(request, response);
    }
    
    // 插入新的專業類別
    private void insertMajorCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("categoryId");
        String name = request.getParameter("categoryName");

        if (idStr != null && !idStr.trim().isEmpty() && name != null && !name.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                MajorCategoryBean newCategory = new MajorCategoryBean(id, name);

                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                HmajorCategoryDAO majorCategoryDAO = new HmajorCategoryDAO(session);
                majorCategoryDAO.insertMajorCategory(newCategory);
                response.sendRedirect("list");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "類別 ID 必須是數字。");
                showNewForm(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "新增類別失敗，可能是 ID 已存在。");
                showNewForm(request, response);
            }
        } else {
            request.setAttribute("error", "類別 ID 和名稱都不能為空。");
            showNewForm(request, response);
        }
    }

    // 刪除專業類別
    private void deleteMajorCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HmajorCategoryDAO majorCategoryDAO = new HmajorCategoryDAO(session);
        majorCategoryDAO.deleteMajorCategory(id);
        response.sendRedirect("list");
    }
 
    // 更新專業類別
    private void updateMajorCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIdStr = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");

        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
            handleError(request, response, "類別 ID 不能為空。");
            return;
        }
        if (categoryName == null || categoryName.trim().isEmpty()) {
            handleError(request, response, "類別名稱不能為空。");
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            handleError(request, response, "無效的類別 ID。");
            return;
        }

        MajorCategoryBean category = new MajorCategoryBean(categoryId, categoryName.trim());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HmajorCategoryDAO majorCategoryDAO = new HmajorCategoryDAO(session);
        if (majorCategoryDAO.updateMajorCategory(category)) {
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "更新失敗。");
            request.setAttribute("category", category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    // 處理錯誤信息
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        showEditForm(request, response);
    }

    // 顯示編輯專業類別表單
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            MajorCategoryBean existingCategory = new MajorCategoryBean(id, name != null ? name : "");
            request.setAttribute("category", existingCategory);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
        dispatcher.forward(request, response);
    }
}