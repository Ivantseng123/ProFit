package com.ProFit.dao.usersDao;

import java.util.List;

import com.ProFit.bean.usersBean.Employer_profile;

public interface IHempProfileDao {

	//新增企業資訊
	Employer_profile saveEmployerInfo(Employer_profile emp);

	//刪除企業資訊BY ID
	boolean deleteEmpInfo(int employer_profile_id);

	void updateEmpInfo(Employer_profile emp);

	List<Employer_profile> getAllEmpInfo();

	Employer_profile getEmpPfInfoByID(int employer_profile_id);

}