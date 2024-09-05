package com.ProFit.controller.usersController;

import java.io.File;
import java.io.IOException;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.dao.usersDao.empApplDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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

		System.out.println(company_taxID_docURL);
		System.out.println(idCard_pictureURL_1);
		System.out.println(idCard_pictureURL_2);

		Integer employer_application_id = Integer.valueOf(request.getParameter("employer_application_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String company_name = request.getParameter("company_name");
		String company_address = request.getParameter("company_address");
		String company_address1 = request.getParameter("company_address1");
		String company_addresstoDB = company_address + company_address1;
		String company_category = request.getParameter("company_category");
		String company_phoneNumber = request.getParameter("company_phoneNumber");
		String company_taxID = request.getParameter("company_taxID");
		String user_national_id = request.getParameter("user_national_id");

		String userFolderPath = "C:\\ProFitServlet\\workspace\\ProFit\\src\\main\\webapp\\usersVIEW\\userupload\\" + user_id;

		File userFolder = new File(userFolderPath);

		if (!userFolder.exists()) {
            userFolder.mkdirs();
        }

		empApplDao empappl = new empApplDao();

        Part filePart1 = request.getPart("taxIDImage");
        Part filePart2 = request.getPart("nationalIDImage1");
        Part filePart3 = request.getPart("nationalIDImage2");


        String uploadPathtoDB1;
        String uploadPathtoDB2;
        String uploadPathtoDB3;
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

        }else if(filePart1 != null && filePart1.getSize() > 0 && filePart2 != null && filePart2.getSize() > 0 &&
        		filePart3 == null && filePart3.getSize() == 0 ){
        	String fileName1 = filePart1.getSubmittedFileName();
        	String fileName2 = filePart2.getSubmittedFileName();

        	String uploadPath1 = userFolderPath + "/" + fileName1;
        	String uploadPath2 = userFolderPath + "/" + fileName2;


        	uploadPathtoDB1 =  user_id + "/" + fileName1;
        	uploadPathtoDB2 =  user_id + "/" + fileName2;
        	uploadPathtoDB3 =  idCard_pictureURL_2;

        	filePart1.write(uploadPath1);
        	filePart2.write(uploadPath2);

		}else if(filePart1.getSize() > 0 && filePart2.getSize() == 0 && filePart3.getSize() > 0) {


        	String fileName1 = filePart1.getSubmittedFileName();
        	String fileName3 = filePart3.getSubmittedFileName();

        	String uploadPath1 = userFolderPath + "/" + fileName1;
        	String uploadPath3 = userFolderPath + "/" + fileName3;

        	uploadPathtoDB1 =  user_id + "/" + fileName1;
        	uploadPathtoDB2 =  idCard_pictureURL_1;
        	uploadPathtoDB3 =  user_id + "/" + fileName3;

        	filePart1.write(uploadPath1);
        	filePart3.write(uploadPath3);

		}else if(filePart1.getSize() > 0 && filePart2.getSize() == 0 &&  filePart3.getSize() == 0){

			String fileName1 = filePart1.getSubmittedFileName();

        	String uploadPath1 = userFolderPath + "/" + fileName1;


        	uploadPathtoDB1 =  user_id + "/" + fileName1;
        	uploadPathtoDB2 =  idCard_pictureURL_1;
        	uploadPathtoDB3 =  idCard_pictureURL_2;

        	filePart1.write(uploadPath1);

		}else if(filePart1.getSize() == 0 && filePart2.getSize() > 0 && filePart3.getSize() > 0) {



        	String fileName2 = filePart2.getSubmittedFileName();
        	String fileName3 = filePart3.getSubmittedFileName();


        	String uploadPath2 = userFolderPath + "/" + fileName2;
        	String uploadPath3 = userFolderPath + "/" + fileName3;

        	uploadPathtoDB1 =  company_taxID_docURL;
        	uploadPathtoDB2 =  user_id + "/" + fileName2;
        	uploadPathtoDB3 =  user_id + "/" + fileName3;


        	filePart2.write(uploadPath2);
        	filePart3.write(uploadPath3);

		} else if(filePart1.getSize() == 0 && filePart2.getSize() > 0 && filePart3.getSize() == 0){


        	String fileName2 = filePart2.getSubmittedFileName();



        	String uploadPath2 = userFolderPath + "/" + fileName2;


        	uploadPathtoDB1 =  company_taxID_docURL;
        	uploadPathtoDB2 =  user_id + "/" + fileName2;
        	uploadPathtoDB3 =  idCard_pictureURL_2;

        	filePart2.write(uploadPath2);


		}else if(filePart1.getSize() == 0 && filePart2.getSize() == 0 && filePart3.getSize() > 0) {

			String fileName3 = filePart3.getSubmittedFileName();

        	String uploadPath3 = userFolderPath + "/" + fileName3;

        	uploadPathtoDB1 =  company_taxID_docURL;
        	uploadPathtoDB2 =  idCard_pictureURL_1;
        	uploadPathtoDB3 =  user_id + "/" + fileName3;

        	filePart3.write(uploadPath3);


		}else {
			uploadPathtoDB1 =  company_taxID_docURL;
        	uploadPathtoDB2 =  idCard_pictureURL_1;
        	uploadPathtoDB3 =  idCard_pictureURL_2;
		}


		Employer_application emp = new Employer_application(employer_application_id,company_name,company_addresstoDB,company_category,company_phoneNumber,company_taxID,uploadPathtoDB1,user_national_id,uploadPathtoDB2,uploadPathtoDB3);


		empappl.updateEmpApplInfo(emp);

		request.getRequestDispatcher("/GetAllempAppl").forward(request, response);

	}





	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
