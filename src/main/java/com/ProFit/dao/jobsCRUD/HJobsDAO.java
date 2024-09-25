package com.ProFit.dao.jobsCRUD;



import com.ProFit.bean.jobsBean.Jobs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class HJobsDAO implements IHJobsDAO {
	
//sessio設置
	@Autowired
	private SessionFactory factory;

	
	// 新增
	@Override
	public Jobs save(Jobs jobs) {
		Session session = factory.getCurrentSession();
		session.persist(jobs);
//		session.flush(); //刷新
		return jobs;
	}

	
	// 刪除
	@Override
	public boolean delete(Integer jobsId) {
		Session session = factory.getCurrentSession();
		Jobs resultBean = session.get(Jobs.class, jobsId);
		if (resultBean != null) {
			session.remove(resultBean);
			session.flush(); //刷新
			return true;
		}
		return false;
	}

	
	// 修改
	@Override
	public boolean update(Jobs jobs) {
		Session session = factory.getCurrentSession();
		Jobs originalJobs = session.get(Jobs.class, jobs.getJobsId());

		if (originalJobs == null) {
			return false;
		}
		BeanUtils.copyProperties(jobs, originalJobs, "jobsId");//copyProperties將jobs複製到originalJobs
		session.merge(originalJobs);
		session.flush();
		return true;
	}

	
	// id查詢
	@Override
	public Jobs findById(Integer jobsId) {
		Session session = factory.getCurrentSession();

		return session.get(Jobs.class, jobsId);
	}

	
	// 查詢全部
	@Override
	public List<Jobs> findAll() {
		Session session = factory.getCurrentSession();
		Query<Jobs> query = session.createQuery("from Jobs", Jobs.class);
		return query.list();
	}


}
