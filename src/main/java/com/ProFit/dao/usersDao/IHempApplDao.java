package com.ProFit.dao.usersDao;

import java.util.List;

import com.ProFit.bean.usersBean.Employer_application;

public interface IHempApplDao {

	//新增企業申請
	Employer_application saveEmpApplInfo(Employer_application emp);

	//刪除企業申請BY ID
	boolean deleteEmpInfo(int employer_application_id);

	boolean updateEmpApplInfo(Employer_application emp);

	boolean updateEmpApplcheck_pass(int employer_application_id);

	boolean updateEmpApplcheck_reject(int employer_application_id);

	List<Employer_application> getAllEmpApplInfo();

	Employer_application getEmpApplInfoByID(int employer_application_id);

}