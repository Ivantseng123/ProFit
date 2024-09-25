package com.ProFit.dao.usersDao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Employer_application;

@Repository
@Transactional
public class HempApplDao implements IHempApplDao {

	@Autowired
	private SessionFactory factory;


	// 新增企業申請
	@Override
	public Employer_application saveEmpApplInfo(Employer_application emp) {
		Session session = factory.getCurrentSession();
		session.persist(emp);
		return emp;
	}

	// 刪除企業申請BY ID
	@Override
	public boolean deleteEmpInfo(int employer_application_id) {
		Session session = factory.getCurrentSession();
		Employer_application resultBean = session.get(Employer_application.class, 
				employer_application_id);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}
	
	// 更新企業申請
	@Override
	public boolean updateEmpApplInfo(Employer_application emp) {
		Session session = factory.getCurrentSession();
		session.merge(emp);
		return true;
	}

	@Override
	public boolean updateEmpApplcheck_pass(int employer_application_id) {
		Session session = factory.getCurrentSession();
		Employer_application emp = session.get(Employer_application.class, employer_application_id);
		
		if (emp != null) {
			emp.setApplicationCheck(1);
			session.merge(emp);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEmpApplcheck_reject(int employer_application_id) {
		Session session = factory.getCurrentSession();
		Employer_application emp = session.get(Employer_application.class, employer_application_id);
		if (emp != null) {

			emp.setApplicationCheck(2);
			session.merge(emp);
			return true;
		}
		
		return false;
	}

	// 查詢全部企業申請
	@Override
	public List<Employer_application> getAllEmpApplInfo() {
		Session session = factory.getCurrentSession();
		List<Employer_application> emps = new ArrayList<>();
		String hql = "SELECT e FROM Employer_application e JOIN e.user u";
		Query<Employer_application> query = session.createQuery(hql, Employer_application.class);
		emps = query.getResultList();
		return emps;
	}

	// 查詢單筆企業申請
	@Override
	public Employer_application getEmpApplInfoByID(int employer_application_id) {
		Session session = factory.getCurrentSession();
		return session.get(Employer_application.class, employer_application_id);
	}
}
