package com.ProFit.service.userService;

import java.util.List;

import com.ProFit.bean.usersBean.Employer_profile;

public interface IEmpPfService {

	// 新增企業資訊
	Employer_profile saveEmployerInfo(Employer_profile emp);

	// 刪除企業資訊BY ID
	boolean deleteEmpInfo(int employer_profile_id);

	void updateEmpInfo(Employer_profile emp);

	List<Employer_profile> getAllEmpInfo();

	Employer_profile getEmpPfInfoByID(int employer_profile_id);

	Employer_profile getEmpPfInfoByUserId(int user_id);

}