package com.ProFit.service.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Employer_application;
import com.ProFit.dao.usersDao.IHempApplDao;

@Service
@Transactional
public class EmpApplService implements IEmpApplService {
	
	@Autowired
	private IHempApplDao eHempApplDao; 
	
	
	@Override
	public List<Employer_application> getAllEmpAppl() {
			
		return eHempApplDao.getAllEmpApplInfo();
	}
	
	@Override
	public Employer_application saveEmpApplInfo(Employer_application emp) {
		
		return eHempApplDao.saveEmpApplInfo(emp);		
	}
	
	@Override
	public boolean deleteEmpInfo(int employer_application_id) {
		
		return eHempApplDao.deleteEmpInfo(employer_application_id);
	}
	
	@Override
	public boolean updateEmpApplInfo(Employer_application emp) {
		return eHempApplDao.updateEmpApplInfo(emp);
	}
	
	@Override
	public boolean updateEmpApplcheck_pass(int employer_application_id) {
		return eHempApplDao.updateEmpApplcheck_pass(employer_application_id);
	}

	@Override
	public boolean updateEmpApplcheck_reject(int employer_application_id) {
		return eHempApplDao.updateEmpApplcheck_reject(employer_application_id);
	}
	
	@Override
	public Employer_application getEmpApplInfoByID(int employer_application_id) {
		return eHempApplDao.getEmpApplInfoByID(employer_application_id);
	}
	
}
