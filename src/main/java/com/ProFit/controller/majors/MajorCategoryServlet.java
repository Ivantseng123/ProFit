package com.ProFit.controller.majors;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ProFit.bean.MajorBean;
import com.ProFit.bean.MajorCategoryBeam;
import com.ProFit.dao.majorsCRUD.MajorCategoryDAO;
import com.ProFit.dao.majorsCRUD.MajorDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/majorCategory/*")
public class MajorCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorCategoryDAO majorCategoryDAO;
	private MajorDAO majorDAO;

	@Override
	public void init() {
		majorCategoryDAO = new MajorCategoryDAO();
		majorDAO = new MajorDAO();
	}

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
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listMajorCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MajorCategoryBeam> listMajorCategory = majorCategoryDAO.findAllMajorCategories();
		request.setAttribute("listMajorCategory", listMajorCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryList.jsp");
		dispatcher.forward(request, response);
	}

	private void listMajorsByCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		List<MajorBean> listMajor = majorDAO.findMajorsByCategoryId(categoryId);
		MajorCategoryBeam category = majorCategoryDAO.findMajorCategoryById(categoryId);

		request.setAttribute("listMajor", listMajor);
		request.setAttribute("category", category);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorListByCategory.jsp");
		dispatcher.forward(request, response);
	}

	// 显示新增表单
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
		dispatcher.forward(request, response);
	}

	// 插入新的 MajorCategory
//    private void insertMajorCategory(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        String name = request.getParameter("name");
//        MajorCategoryBeam newCategory = new MajorCategoryBeam();
//        newCategory.setCategoryName(name);
//        majorCategoryDAO.insertMajorCategory(newCategory);
//        response.sendRedirect("list");
//    }
	private void insertMajorCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String idStr = request.getParameter("categoryId");
		String name = request.getParameter("categoryName");

		if (idStr != null && !idStr.trim().isEmpty() && name != null && !name.trim().isEmpty()) {
			try {
				int id = Integer.parseInt(idStr);
				MajorCategoryBeam newCategory = new MajorCategoryBeam();
				newCategory.setMajorCategoryId(id);
				newCategory.setCategoryName(name);

				if (majorCategoryDAO.insertMajorCategory(newCategory)) {
					response.sendRedirect("list");
				} else {
					request.setAttribute("error", "新增類別失敗，可能是 ID 已存在。");
					showNewForm(request, response);
				}
			} catch (NumberFormatException e) {
				request.setAttribute("error", "類別 ID 必須是數字。");
				showNewForm(request, response);
			}
		} else {
			request.setAttribute("error", "類別 ID 和名稱都不能為空。");
			showNewForm(request, response);
		}
	}

	// 删除 MajorCategory
	private void deleteMajorCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		majorCategoryDAO.deleteMajorCategory(id);
		response.sendRedirect("list");
	}

//	// 更改 MajorCategory
//	private void updateMajorCategory(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		String categoryIdStr = request.getParameter("categoryId");
////		int id = Integer.parseInt(request.getParameter("categoryId"));
//        String name = request.getParameter("categoryName");
//
//        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
//            handleError(request, response, "類別 ID 不能為空。");
//            return;
//        }
//        if (name == null || name.trim().isEmpty()) {
//            handleError(request, response, "類別名稱不能為空。");
//            return;
//        }
//
//        int categoryId;
//        try {
//            categoryId = Integer.parseInt(categoryIdStr);
//        } catch (NumberFormatException e) {
//            handleError(request, response, "無效的類別 ID。");
//            return;
//        }
//
//        MajorCategoryBeam category = new MajorCategoryBeam();
//        category.setMajorCategoryId(categoryId);
//        category.setCategoryName(name.trim());
//
//        if (majorCategoryDAO.updateMajorCategory(category)) {
//            response.sendRedirect("list");
//        } else {
//            request.setAttribute("error", "更新失敗。");
//            showEditForm(request, response);
//        }
//	}
	private void updateMajorCategory(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
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

	    MajorCategoryBeam category = new MajorCategoryBeam();
	    category.setMajorCategoryId(categoryId);
	    category.setCategoryName(categoryName.trim());

	    if (majorCategoryDAO.updateMajorCategory(category)) {
	        response.sendRedirect("list");
	    } else {
	        request.setAttribute("error", "更新失敗。");
	        request.setAttribute("category", category);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
	        dispatcher.forward(request, response);
	    }
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
	        throws ServletException, IOException, SQLException {
	    request.setAttribute("error", errorMessage);
	    showEditForm(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, ServletException, IOException {
	    String idStr = request.getParameter("id");
	    String name = request.getParameter("name");

	    if (idStr != null && !idStr.isEmpty()) {
	        int id = Integer.parseInt(idStr);
	        MajorCategoryBeam existingCategory = new MajorCategoryBeam();
	        existingCategory.setMajorCategoryId(id);
	        existingCategory.setCategoryName(name != null ? name : "");

	        request.setAttribute("category", existingCategory);
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/majorsVIEW/MajorCategoryForm.jsp");
	    dispatcher.forward(request, response);
	}
}
