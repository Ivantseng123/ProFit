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
import com.ProFit.dao.majorsCRUD.MajorDAO;

@WebServlet("/major/*")
public class MajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorDAO majorDAO;

	public void init() {
		majorDAO = new MajorDAO();
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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertMajor(request, response);
				break;
			case "/delete":
				deleteMajor(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateMajor(request, response);
				break;
			case "/view":
				viewMajor(request, response);
				break;
			 case "/category":
                 listMajorsByCategory(request, response);
                 break;
			default:
				listMajor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listMajor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MajorBean> listMajor = majorDAO.findAllMajors();
		for (MajorBean major : listMajor) {
	        String categoryName = majorDAO.getCategoryNameById(major.getMajorCategoryId());
	        major.setCategoryName(categoryName); // 假設您在 MajorBean 中添加了 categoryName 字段
	    }
		request.setAttribute("listMajor", listMajor);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MajorBean existingMajor = majorDAO.findMajorById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp");
		request.setAttribute("major", existingMajor);
		dispatcher.forward(request, response);
	}

//	private void insertMajor(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//		String name = request.getParameter("majorName");
//		int categoryId = Integer.parseInt(request.getParameter("majorCategoryId"));
//		String description = request.getParameter("majorDescription");
//		MajorBean newMajor = new MajorBean();
//		newMajor.setMajorName(name);
//		newMajor.setMajorCategoryId(categoryId);
//		newMajor.setMajorDescription(description);
//		majorDAO.insertMajor(newMajor);
//		response.sendRedirect("list");
//	}
	private void insertMajor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String idStr = request.getParameter("majorId");
        String name = request.getParameter("majorName");
        String categoryIdStr = request.getParameter("majorCategoryId");
        String description = request.getParameter("majorDescription");
        
        MajorBean newMajor = new MajorBean();
        
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                newMajor.setMajorId(id);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "無效的Major ID, 請輸入有效數字");
                request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "需填寫Major ID, 請輸入有效數字");
            request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
            return;
        }
        
        newMajor.setMajorName(name);
        
        if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                newMajor.setMajorCategoryId(categoryId);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "無效的Category ID, 請輸入有效數字");
                request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "需填寫Category ID, 請輸入有效數字");
            request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
            return;
        }
        
        newMajor.setMajorDescription(description);
        
        if (majorDAO.insertMajor(newMajor)) {
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "新增major失敗, 請再試一次");
            request.getRequestDispatcher("/majorsVIEW/MajorForm.jsp").forward(request, response);
        }
    }
	private void updateMajor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("majorId"));
		String name = request.getParameter("majorName");
		int categoryId = Integer.parseInt(request.getParameter("majorCategoryId"));
		String description = request.getParameter("majorDescription");

		MajorBean major = new MajorBean();
		major.setMajorId(id);
		major.setMajorName(name);
		major.setMajorCategoryId(categoryId);
		major.setMajorDescription(description);
		majorDAO.updateMajor(major);
		response.sendRedirect("list");
	}

	private void deleteMajor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		majorDAO.deleteMajor(id);
		response.sendRedirect("list");
	}

	private void viewMajor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MajorBean major = majorDAO.findMajorById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorView.jsp");
		request.setAttribute("major", major);
		dispatcher.forward(request, response);
	}
	
	 private void listMajorsByCategory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        String categoryIdStr = request.getParameter("categoryId");
	        if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
	            try {
	                int categoryId = Integer.parseInt(categoryIdStr);
	                List<MajorBean> majors = majorDAO.findMajorsByCategoryId(categoryId);
	                for (MajorBean major : majors) {
	        	        String categoryName = majorDAO.getCategoryNameById(major.getMajorCategoryId());
	        	        major.setCategoryName(categoryName); // 假設您在 MajorBean 中添加了 categoryName 字段
	        	    }
	                request.setAttribute("listMajor", majors);
	                request.setAttribute("categoryId", categoryId);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
	                dispatcher.forward(request, response);
	            } catch (NumberFormatException e) {
	                request.setAttribute("error", "Invalid category ID. Please provide a valid number.");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
	                dispatcher.forward(request, response);
	            }
	        } else {
	            request.setAttribute("error", "Category ID is required.");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorList.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
}
