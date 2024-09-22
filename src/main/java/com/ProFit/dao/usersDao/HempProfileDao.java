package com.ProFit.dao.usersDao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.usersBean.Employer_profile;


@Repository
@Transactional
public class HempProfileDao implements IHempProfileDao {
	
	@Autowired
	private SessionFactory factory;


	// 新增企業資訊
	@Override
	public Employer_profile saveEmployerInfo(Employer_profile emp) {
		Session session = factory.getCurrentSession();
		session.persist(emp);
		return emp;
	}

	// 刪除企業資訊BY ID
	@Override
	public boolean deleteEmpInfo(int employer_profile_id) {
		Session session = factory.getCurrentSession();
		Employer_profile resultBean = session.get(Employer_profile.class, 
				employer_profile_id);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 更新企業資訊
	@Override
	public void updateEmpInfo(Employer_profile emp) {
		Session session = factory.getCurrentSession();
		session.merge(emp);
	}
	
	//查詢全部企業資訊
	@Override
	public List<Employer_profile> getAllEmpInfo() {
		Session session = factory.getCurrentSession();
		List<Employer_profile> emps = new ArrayList<>();
		String hql = "SELECT e FROM Employer_profile e JOIN e.user u";
		Query<Employer_profile> query = session.createQuery(hql, Employer_profile.class);
		emps = query.getResultList();
		return emps;
	}
	
	//查詢單筆企業資訊
	@Override
	public Employer_profile getEmpPfInfoByID(int employer_profile_id) {
		Session session = factory.getCurrentSession();
		return session.get(Employer_profile.class, employer_profile_id);
	}

	@Override
	public Employer_profile getEmpPfInfoByUserId(int user_id) {
		Session session = factory.getCurrentSession();
		 String hql = "FROM Employer_profile e WHERE e.userId = :userId";
		   return session.createQuery(hql, Employer_profile.class)
		                  .setParameter("userId", user_id)
		                  .uniqueResult();
	}
}
