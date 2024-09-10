package com.ProFit.controller.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.ProFit.bean.servicesBean.ServiceBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.dao.servicesCRUD.HServiceDAO;
import com.ProFit.hibernateutil.HibernateUtil;

import org.hibernate.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/service/*")
@MultipartConfig(maxFileSize = 16177215)
public class ServiceServlet extends HttpServlet {
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
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listServices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        Map<Integer, String> users = serviceDAO.getAllUsers();
        Map<Integer, String> majors = serviceDAO.getAllMajors();
        request.setAttribute("users", users);
        request.setAttribute("majors", majors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        String title = request.getParameter("serviceTitle");
        String content = request.getParameter("serviceContent");
        int price = Integer.parseInt(request.getParameter("servicePrice"));
        String unitName = request.getParameter("serviceUnitName");
        double duration = Double.parseDouble(request.getParameter("serviceDuration"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int majorId = Integer.parseInt(request.getParameter("majorId"));

        Users user = session.get(Users.class, userId);
        MajorBean major = session.get(MajorBean.class, majorId);
        UserMajorPK userMajorPK = new UserMajorPK(user, major);
        UserMajorBean userMajor = session.get(UserMajorBean.class, userMajorPK);

        ServiceBean newService = new ServiceBean();
        newService.setUserMajorBean(userMajor);
        newService.setServiceTitle(title);
        newService.setServiceContent(content);
        newService.setServicePrice(price);
        newService.setServiceUnitName(unitName);
        newService.setServiceDuration(duration);
        newService.setServiceCreateDate(LocalDateTime.now());
        newService.setServiceUpdateDate(LocalDateTime.now());

        Part filePart1 = request.getPart("serviceSample1");
        if (filePart1 != null && filePart1.getSize() > 0) {
            newService.setServicePictureURL1(saveFile(filePart1));
        }

        Part filePart2 = request.getPart("serviceSample2");
        if (filePart2 != null && filePart2.getSize() > 0) {
            newService.setServicePictureURL2(saveFile(filePart2));
        }

        Part filePart3 = request.getPart("serviceSample3");
        if (filePart3 != null && filePart3.getSize() > 0) {
            newService.setServicePictureURL3(saveFile(filePart3));
        }

        serviceDAO.insertService(newService);
        response.sendRedirect("search");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        int id = Integer.parseInt(request.getParameter("id"));
        ServiceBean existingService = serviceDAO.findServiceById(id);
        Map<Integer, String> users = serviceDAO.getAllUsers();
        Map<Integer, String> majors = serviceDAO.getMajorsByUserId(existingService.getUserMajorBean().getId().getUser().getUserId());

        request.setAttribute("service", existingService);
        request.setAttribute("users", users);
        request.setAttribute("majors", majors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceEditForm.jsp");
        dispatcher.forward(request, response);
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        int id = Integer.parseInt(request.getParameter("serviceId"));
        String title = request.getParameter("serviceTitle");
        String content = request.getParameter("serviceContent");
        int price = Integer.parseInt(request.getParameter("servicePrice"));
        String unitName = request.getParameter("serviceUnitName");
        double duration = Double.parseDouble(request.getParameter("serviceDuration"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int majorId = Integer.parseInt(request.getParameter("majorId"));

        Users user = session.get(Users.class, userId);
        MajorBean major = session.get(MajorBean.class, majorId);
        UserMajorPK userMajorPK = new UserMajorPK(user, major);
        UserMajorBean userMajor = session.get(UserMajorBean.class, userMajorPK);

        ServiceBean service = serviceDAO.findServiceById(id);
        service.setUserMajorBean(userMajor);
        service.setServiceTitle(title);
        service.setServiceContent(content);
        service.setServicePrice(price);
        service.setServiceUnitName(unitName);
        service.setServiceDuration(duration);
        service.setServiceUpdateDate(LocalDateTime.now());

        Part filePart1 = request.getPart("serviceSample1");
        if (filePart1 != null && filePart1.getSize() > 0) {
            service.setServicePictureURL1(saveFile(filePart1));
        }

        Part filePart2 = request.getPart("serviceSample2");
        if (filePart2 != null && filePart2.getSize() > 0) {
            service.setServicePictureURL2(saveFile(filePart2));
        }

        Part filePart3 = request.getPart("serviceSample3");
        if (filePart3 != null && filePart3.getSize() > 0) {
            service.setServicePictureURL3(saveFile(filePart3));
        }

        serviceDAO.updateService(service);
        response.sendRedirect("search");
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        int id = Integer.parseInt(request.getParameter("id"));
        serviceDAO.deleteService(id);
        response.sendRedirect("search");
    }

    private void searchServices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

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
        } else {
            Map<Integer, String> majors = serviceDAO.getAllMajors();
            request.setAttribute("majors", majors);
            request.setAttribute("selectedUserId", userId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceList.jsp");
        dispatcher.forward(request, response);
    }

    private void viewService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

        int id = Integer.parseInt(request.getParameter("id"));
        ServiceBean service = serviceDAO.findServiceById(id);
        if (service != null) {
            request.setAttribute("service", service);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/servicesVIEW/ServiceView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("list");
        }
    }

    private void selectUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HServiceDAO serviceDAO = new HServiceDAO(session);

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

    private String saveFile(Part filePart) {
        // 實現文件保存邏輯，返回文件URL
        // 這裡需要根據您的具體需求來實現
        return "file_url_placeholder";
    }
}