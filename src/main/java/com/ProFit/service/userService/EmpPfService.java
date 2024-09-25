package com.ProFit.service.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ProFit.bean.usersBean.Employer_profile;
import com.ProFit.dao.usersDao.IHempProfileDao;

@Service
@Transactional
public class EmpPfService implements IEmpPfService {

	@Autowired
	private IHempProfileDao eDao;

	// 新增企業資訊
	@Override
	public Employer_profile saveEmployerInfo(Employer_profile emp) {
		return eDao.saveEmployerInfo(emp);
	}

	// 刪除企業資訊BY ID
	@Override
	public boolean deleteEmpInfo(int employer_profile_id) {
		return eDao.deleteEmpInfo(employer_profile_id);
	}

	@Override
	public void updateEmpInfo(Employer_profile emp) {
		eDao.updateEmpInfo(emp);
	}

	@Override
	public List<Employer_profile> getAllEmpInfo() {
		return eDao.getAllEmpInfo();
	}

	@Override
	public Employer_profile getEmpPfInfoByID(int employer_profile_id) {
		return eDao.getEmpPfInfoByID(employer_profile_id);
	}

	@Override
	public Employer_profile getEmpPfInfoByUserId(int user_id) {
		return eDao.getEmpPfInfoByUserId(user_id);
	}

}
