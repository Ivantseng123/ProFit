package com.ProFit.controller.usersController;

import java.io.File;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.empProfileDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateEmpPf")
@MultipartConfig
public class UpdateEmpPf extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateEmpPf() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String company_photoURL = request.getParameter("company_photoURL");

		Integer employer_profile_id = Integer.valueOf(request.getParameter("employer_profile_id"));
		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		String company_name = request.getParameter("company_name");
		String company_address = request.getParameter("company_address");
		String company_address1 = request.getParameter("company_address1");
		String company_addresstoDB = company_address + company_address1;
		String company_category = request.getParameter("company_category");
		String company_phoneNumber = request.getParameter("company_phoneNumber");
		String company_taxID = request.getParameter("company_taxID");
		String company_numberOfemployee = request.getParameter("company_numberOfemployee");
		String company_captital = request.getParameter("company_captital");
		String company_description = request.getParameter("company_description");

		String userFolderPath = "C:\\ProFitServlet\\workspace\\ProFit\\src\\main\\webapp\\usersVIEW\\userupload\\" + user_id;

		File userFolder = new File(userFolderPath);

		if (!userFolder.exists()) {
            userFolder.mkdirs();
        }

        Part filePart = request.getPart("companyPhoto");
        String uploadPathtoDB;

        System.out.println(filePart.getSubmittedFileName());
        if(filePart.getSize() > 0) {
        	String fileName = filePart.getSubmittedFileName();
        	String uploadPath = userFolderPath + "/" + fileName;
        	uploadPathtoDB =  user_id + "/" + fileName;
        	filePart.write(uploadPath);
        }else {
        	uploadPathtoDB = company_photoURL;
        }


        empProfileDao empdao = new empProfileDao();

		Employer_profile emp = new Employer_profile(employer_profile_id,user_id,company_name,company_addresstoDB,company_category,company_phoneNumber,company_taxID,company_numberOfemployee,company_captital,company_description,uploadPathtoDB);

		empdao.updateEmpInfo(emp);

		request.getRequestDispatcher("/GetAllEmpPf").forward(request, response);

	}





	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
