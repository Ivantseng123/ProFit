package com.ProFit.controller.usersController;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.dao.usersDao.HempApplDao;
import com.ProFit.util.hibernateutil.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmpAppl")
@MultipartConfig
public class UpdateEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateEmpAppl() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String company_taxID_docURL = request.getParameter("company_taxID_docURL");
		String idCard_pictureURL_1 = request.getParameter("idCard_pictureURL_1");
		String idCard_pictureURL_2 = request.getParameter("idCard_pictureURL_2");

//		System.out.println(company_taxID_docURL);
//		System.out.println(idCard_pictureURL_1);
//		System.out.println(idCard_pictureURL_2);

		Integer employer_application_id = Integer.valueOf(request.getParameter("employer_application_id"));
		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		String company_name = request.getParameter("company_name");
		String company_address = request.getParameter("company_address");
		String company_address1 = request.getParameter("company_address1");
		String company_addresstoDB = company_address + company_address1;
		String company_category = request.getParameter("company_category");
		String company_phoneNumber = request.getParameter("company_phoneNumber");
		String company_taxID = request.getParameter("company_taxID");
		String user_national_id = request.getParameter("user_national_id");

		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HempApplDao empappl = new HempApplDao(session);

		Employer_application emp = new Employer_application(employer_application_id,user_id,company_name,company_addresstoDB,company_category,company_phoneNumber,company_taxID,company_taxID_docURL,user_national_id,idCard_pictureURL_1,idCard_pictureURL_2);


		empappl.updateEmpApplInfo(emp);
		
		session.flush();  // 將緩存的更改同步到數據庫
		session.clear();  // 清空緩存，強制從數據庫重新查詢

		request.getRequestDispatcher("/GetAllempAppl").forward(request, response);

	}





	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
