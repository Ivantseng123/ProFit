package com.ProFit.dao.usersDao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.ProFit.bean.usersBean.Employer_profile;

public class HempProfileDao implements IHempProfileDao {

	private Session session;

	public HempProfileDao(Session session) {
		this.session = session;
	}

	// 新增企業資訊
	@Override
	public Employer_profile saveEmployerInfo(Employer_profile emp) {
		session.persist(emp);
		return emp;
	}

	// 刪除企業資訊BY ID
	@Override
	public boolean deleteEmpInfo(int employer_profile_id) {
		Employer_profile resultBean = session.get(Employer_profile.class, employer_profile_id);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	@Override
	public void updateEmpInfo(Employer_profile emp) {
		session.merge(emp);
	}

	@Override
	public List<Employer_profile> getAllEmpInfo() {
		List<Employer_profile> emps = new ArrayList<>();

		String hql = "SELECT e FROM Employer_profile e JOIN e.user u";

		Query<Employer_profile> query = session.createQuery(hql, Employer_profile.class);
		emps = query.getResultList();

		return emps;
	}

	@Override
	public Employer_profile getEmpPfInfoByID(int employer_profile_id) {
		return session.get(Employer_profile.class, employer_profile_id);
	}

	@Override
	public Employer_profile getEmpPfInfoByUserId(int user_id) {
		 String hql = "FROM Employer_profile e WHERE e.userId = :userId";
		   return session.createQuery(hql, Employer_profile.class)
		                  .setParameter("userId", user_id)
		                  .uniqueResult();
	}
}
