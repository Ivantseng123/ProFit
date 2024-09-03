package com.ProFit.controller.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.sql.Blob;
import java.sql.SQLException;

import com.ProFit.bean.MajorBean;
import com.ProFit.bean.MajorCategoryBeam;
import com.ProFit.bean.ServiceBean;
import com.ProFit.dao.majorsCRUD.MajorCategoryDAO;
import com.ProFit.dao.majorsCRUD.MajorDAO;
import com.ProFit.dao.servicesCRUD.ServiceDAO;

@WebServlet("/service/*")
@MultipartConfig(maxFileSize = 16177215)
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDAO serviceDAO;

	public void init() {
		serviceDAO = new ServiceDAO();
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
				insertService(request, response);
				break;
			case "/delete":
				deleteService(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateService(request, response);
				break;
			case "/search":
				searchServices(request, response);
				break;
			case "/view":
				viewService(request, response);
				break;
			case "/selectUser":
				selectUser(request, response);
				break;
			default:
				listServices(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listServices(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ServiceBean> listService = serviceDAO.findAllServices();
		Map<Integer, String> users = serviceDAO.getAllUsers();
		request.setAttribute("listService", listService);
		request.setAttribute("users", users);

		String userIdParam = request.getParameter("userId");
		if (userIdParam != null && !userIdParam.isEmpty()) {
			int userId = Integer.parseInt(userIdParam);
			Map<Integer, String> majors = serviceDAO.getMajorsByUserId(userId);
			request.setAttribute("majors", majors);
			request.setAttribute("selectedUserId", userId);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, String> users;
		Map<Integer, String> majors;
		try {
			users = serviceDAO.getAllUsers();
			request.setAttribute("users", users);
			majors = serviceDAO.getAllMajors();
			request.setAttribute("majors", majors);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceForm.jsp");
		dispatcher.forward(request, response);
	}

	private void insertService(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String title = request.getParameter("serviceTitle");
		String content = request.getParameter("serviceContent");
		double price = Double.parseDouble(request.getParameter("servicePrice"));
		String unitName = request.getParameter("serviceUnitName");
		double duration = Double.parseDouble(request.getParameter("serviceDuration"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int majorId = Integer.parseInt(request.getParameter("majorId"));

		ServiceBean newService = new ServiceBean();
		newService.setServiceTitle(title);
		newService.setServiceContent(content);
		newService.setServicePrice(price);
		newService.setServiceUnitName(unitName);
		newService.setServiceDuration(duration);
		newService.setUserId(userId);
		newService.setMajorId(majorId);

		Part filePart1 = request.getPart("serviceSample1");
		if (filePart1 != null && filePart1.getSize() > 0) {
			newService.setServiceSample1(filePart1.getInputStream().readAllBytes());
		}

		Part filePart2 = request.getPart("serviceSample2");
		if (filePart2 != null && filePart2.getSize() > 0) {
			newService.setServiceSample2(filePart2.getInputStream().readAllBytes());
		}

		Part filePart3 = request.getPart("serviceSample3");
		if (filePart3 != null && filePart3.getSize() > 0) {
			newService.setServiceSample3(filePart3.getInputStream().readAllBytes());
		}

		serviceDAO.insertService(newService);
		response.sendRedirect("search");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ServiceBean existingService = serviceDAO.findServiceById(id);
		Map<Integer, String> users = serviceDAO.getAllUsers();
		Map<Integer, String> majors = serviceDAO.getMajorsByUserId(existingService.getUserId());
//		Map<Integer, String> majors = serviceDAO.getAllMajors();
//		request.setAttribute("majors", majors);
		request.setAttribute("service", existingService);
		request.setAttribute("users", users);
		request.setAttribute("majors", majors);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceEditFrom.jsp");
		dispatcher.forward(request, response);
	}

	private void updateService(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("serviceId"));
		String title = request.getParameter("serviceTitle");
		String content = request.getParameter("serviceContent");
		double price = Double.parseDouble(request.getParameter("servicePrice"));
		String unitName = request.getParameter("serviceUnitName");
		double duration = Double.parseDouble(request.getParameter("serviceDuration"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int majorId = Integer.parseInt(request.getParameter("majorId"));

		ServiceBean service = new ServiceBean();
		service.setServiceId(id);
		service.setServiceTitle(title);
		service.setServiceContent(content);
		service.setServicePrice(price);
		service.setServiceUnitName(unitName);
		service.setServiceDuration(duration);
		service.setUserId(userId);
		service.setMajorId(majorId);

		Part filePart1 = request.getPart("serviceSample1");
		if (filePart1 != null && filePart1.getSize() > 0) {
			service.setServiceSample1(filePart1.getInputStream().readAllBytes());
		}

		Part filePart2 = request.getPart("serviceSample2");
		if (filePart2 != null && filePart2.getSize() > 0) {
			service.setServiceSample2(filePart2.getInputStream().readAllBytes());
		}

		Part filePart3 = request.getPart("serviceSample3");
		if (filePart3 != null && filePart3.getSize() > 0) {
			service.setServiceSample3(filePart3.getInputStream().readAllBytes());
		}

		serviceDAO.updateService(service);
		response.sendRedirect("search");
	}

	private void deleteService(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		serviceDAO.deleteService(id);
		response.sendRedirect("search");
	}

	private void searchServices(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String titleKeyword = request.getParameter("titleKeyword");
		String contentKeyword = request.getParameter("contentKeyword");
		Integer userId = null;
		Integer majorId = null;

		String userIdStr = request.getParameter("userId");
		if (userIdStr != null && !userIdStr.isEmpty()) {
			userId = Integer.parseInt(userIdStr);
		}

		String majorIdStr = request.getParameter("majorId");
		if (majorIdStr != null && !majorIdStr.isEmpty()) {
			majorId = Integer.parseInt(majorIdStr);
		}

		List<ServiceBean> searchResult = serviceDAO.searchServices(titleKeyword, contentKeyword, majorId, userId);
		Map<Integer, String> users = serviceDAO.getAllUsers();
		request.setAttribute("listService", searchResult);
		request.setAttribute("users", users);

		if (userId != null) {
			Map<Integer, String> majors = serviceDAO.getMajorsByUserId(userId);
			request.setAttribute("majors", majors);
			request.setAttribute("selectedUserId", userId);
		} else if (userId == null) {
			Map<Integer, String> majors = serviceDAO.getAllMajors();
			request.setAttribute("majors", majors);
			request.setAttribute("selectedUserId", userId);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceList.jsp");
		dispatcher.forward(request, response);
	}

	private void viewService(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ServiceBean service = serviceDAO.findServiceById(id);
		if (service != null) {
			request.setAttribute("service", service);

			// 獲取用戶名和專業名稱
			String userName = serviceDAO.getUserNameById(service.getUserId());
			String majorName = serviceDAO.getMajorNameById(service.getMajorId());
			request.setAttribute("userName", userName);
			request.setAttribute("majorName", majorName);
//	            System.out.println("serviceCreateDate: " + service.getUserId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceView.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("list");
		}
	}

	//用戶選擇
	private void selectUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String userIdStr = request.getParameter("userId");
		if (userIdStr != null && !userIdStr.isEmpty()) {
			int userId = Integer.parseInt(userIdStr);
			Map<Integer, String> majors = serviceDAO.getMajorsByUserId(userId);
			Map<Integer, String> users = serviceDAO.getAllUsers();

			request.setAttribute("selectedUserId", userId);
			request.setAttribute("majors", majors);
			request.setAttribute("users", users);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceForm.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/service/new");
		}
	}
}
