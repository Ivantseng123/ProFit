package com.ProFit.controller.usersController;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.HempProfileDao;
import com.ProFit.dao.usersDao.IHempProfileDao;
import com.ProFit.hibernateutil.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertEmpPf")
public class InsertEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public InsertEmpPf() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		String company_name = request.getParameter("company_name");
		String company_address = request.getParameter("company_address");
		String company_address1 = request.getParameter("company_address1");
		String company_addresstoDB = company_address + company_address1;
		String company_category = request.getParameter("company_category");

		String company_phoneNumber = request.getParameter("company_phoneNumber");
		String company_taxID = request.getParameter("company_taxID");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		IHempProfileDao empdao = new HempProfileDao(session);


		try {
			Employer_profile emp = new Employer_profile(user_id,company_name,company_addresstoDB,company_category,company_phoneNumber,company_taxID);

			emp.toString();

			empdao.saveEmployerInfo(emp);
			
			session.flush();  // 將緩存的更改同步到數據庫
			session.clear();  // 清空緩存，強制從數據庫重新查詢

			request.getRequestDispatcher("/GetAllEmpPf").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
