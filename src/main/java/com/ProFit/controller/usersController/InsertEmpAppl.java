package com.ProFit.controller.usersController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_application;

import com.ProFit.dao.usersDao.empApplDao;

@WebServlet("/InsertEmpAppl")
@MultipartConfig
public class InsertEmpAppl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertEmpAppl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		Integer user_id = Integer.valueOf(request.getParameter("user_id")) ;
		String company_name = request.getParameter("company_name");
		String company_phoneNumber = request.getParameter("company_phoneNumber");
		String company_taxID = request.getParameter("company_taxID");
		String company_address = request.getParameter("company_address");
		String company_address1 = request.getParameter("company_address1");
		String company_category = request.getParameter("company_category");
		String user_national_id = request.getParameter("user_national_id");
		String company_addresstoDB = company_address + company_address1;	 	
		
		String userFolderPath = "C:\\ProFitServlet\\workspace\\ProFit\\src\\main\\webapp\\usersVIEW\\userupload\\" + user_id;
		
		File userFolder = new File(userFolderPath);
		
		if (!userFolder.exists()) {
            userFolder.mkdirs();
        }
        Part filePart1 = request.getPart("taxIDImage");
        
        Part filePart2 = request.getPart("nationalIDImage1");
        Part filePart3 = request.getPart("nationalIDImage2");
        
        
        String uploadPathtoDB1 = null;
        String uploadPathtoDB2 = null;
        String uploadPathtoDB3 = null;
        if(filePart1 != null && filePart1.getSize() > 0 && filePart2 != null && filePart2.getSize() > 0 &&
        		filePart3 != null && filePart3.getSize() > 0 ) {
        	
        	String fileName1 = filePart1.getSubmittedFileName();
        	String fileName2 = filePart2.getSubmittedFileName();
        	String fileName3 = filePart3.getSubmittedFileName();
        	
        	String uploadPath1 = userFolderPath + "/" + fileName1;
        	String uploadPath2 = userFolderPath + "/" + fileName2;
        	String uploadPath3 = userFolderPath + "/" + fileName3;
        	
        	uploadPathtoDB1 =  user_id + "/" + fileName1;	
        	uploadPathtoDB2 =  user_id + "/" + fileName2;
        	uploadPathtoDB3 =  user_id + "/" + fileName3;
        	
        	filePart1.write(uploadPath1);	
        	filePart2.write(uploadPath2);	
        	filePart3.write(uploadPath3);	
			
			empApplDao empappl = new empApplDao();
			
			try {
				Employer_application emp = new Employer_application(user_id,company_name,company_addresstoDB,company_category,company_phoneNumber,company_taxID,uploadPathtoDB1,user_national_id,uploadPathtoDB2,uploadPathtoDB3,0);
				
				System.out.println(emp.toString());
				
				empappl.saveEmpApplInfo(emp);

				request.getRequestDispatcher("/GetAllempAppl").forward(request, response);
			} catch (Exception e) {

			}
		}
	}
		
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
