package com.ProFit.service.userService;

import java.util.List;

import com.ProFit.bean.usersBean.Employer_application;

public interface IEmpApplService {

	List<Employer_application> getAllEmpAppl();

	Employer_application saveEmpApplInfo(Employer_application emp);

	boolean deleteEmpInfo(int employer_application_id);

	boolean updateEmpApplInfo(Employer_application emp);

	boolean updateEmpApplcheck_pass(int employer_application_id);

	boolean updateEmpApplcheck_reject(int employer_application_id);

	Employer_application getEmpApplInfoByID(int employer_application_id);

}