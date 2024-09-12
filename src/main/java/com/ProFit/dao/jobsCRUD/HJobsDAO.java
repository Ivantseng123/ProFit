package com.ProFit.dao.jobsCRUD;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.ProFit.bean.Jobs;
import com.ProFit.dao.jobsCRUD.IHJobsDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HJobsDAO implements IHJobsDAO {
	
//sessio設置
	private Session session;

	public HJobsDAO(Session session) {
		this.session = session;
	}

	
	// 新增
	@Override
	public Jobs save(Jobs jobs) {
//		session.persist(jobs);
//		return jobs;
		Transaction t = session.beginTransaction();
        try {
            session.persist(jobs);
            t.commit();
            return jobs;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return null;
        }
	}

	
	// 刪除
	@Override
	public boolean delete(Integer jobsId) {
		Jobs jobs = session.get(Jobs.class, jobsId);
		if (jobs != null) {
			Transaction t = session.beginTransaction();
	        try {
	        	session.remove(jobs);
	            return true;
	        } catch (Exception e) {
	            if (t != null) {
	                t.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        }
		}
		return false;
	}

	
	// 修改
	@Override
	public boolean update(Jobs jobs) {
		Jobs oldjobs = session.get(Jobs.class, jobs.getJobsId());

		if (oldjobs == null) {
			System.out.println("jobs with ID " + jobs.getJobsId() + " does not exist.");
			return false;
		}
		
		// 對比屬性值，並進行更新操作
//		oldjobs.setJobsUserId(jobs.getJobsUserId() == null
//				? oldjobs.getJobsUserId()
//				: jobs.getJobsUserId());

		oldjobs.setJobsRequiredSkills(jobs.getJobsRequiredSkills() == null || jobs.getJobsRequiredSkills().isEmpty()
				? oldjobs.getJobsRequiredSkills()
				: jobs.getJobsRequiredSkills());

		oldjobs.setJobsTitle(jobs.getJobsTitle() == null || jobs.getJobsTitle().isEmpty()
				? oldjobs.getJobsTitle()
				: jobs.getJobsTitle());

		oldjobs.setJobsPostingDate(jobs.getJobsPostingDate() == null 
				? oldjobs.getJobsPostingDate()
				: jobs.getJobsPostingDate());

		oldjobs.setJobsApplicationDeadline(jobs.getJobsApplicationDeadline() == null 
				? oldjobs.getJobsApplicationDeadline()
				: jobs.getJobsApplicationDeadline());

		oldjobs.setJobsDescription(jobs.getJobsDescription() == null || jobs.getJobsDescription().toString().isEmpty()
				? oldjobs.getJobsDescription()
				: jobs.getJobsDescription());

		oldjobs.setJobsStatus(jobs.getJobsStatus() == null || jobs.getJobsStatus().toString().isEmpty()
				? oldjobs.getJobsStatus()
				: jobs.getJobsStatus());

		oldjobs.setJobsLocation(jobs.getJobsLocation() == null || jobs.getJobsLocation().toString().isEmpty()
				? oldjobs.getJobsLocation()
				: jobs.getJobsLocation());

		oldjobs.setJobsMaxSalary(jobs.getJobsMaxSalary() == null
				? oldjobs.getJobsMaxSalary()
				: jobs.getJobsMaxSalary());

		oldjobs.setJobsMinSalary(jobs.getJobsMinSalary() == null
				? oldjobs.getJobsMinSalary()
				: jobs.getJobsMinSalary());

		oldjobs.setJobsWorktime(jobs.getJobsWorktime() == null || jobs.getJobsWorktime().isEmpty()
				? oldjobs.getJobsWorktime()
				: jobs.getJobsWorktime());

		oldjobs.setJobsNumberOfOpenings(jobs.getJobsNumberOfOpenings() == null
				? oldjobs.getJobsNumberOfOpenings()
				: jobs.getJobsNumberOfOpenings());

		Transaction t = session.beginTransaction();
        try {
    		session.merge(oldjobs);
            t.commit();
            return true;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return false;
        }
	}

	
	// id查詢
	@Override
	public Jobs findById(Integer jobsId) {
		return session.get(Jobs.class, jobsId);
	}

	
	// 查詢全部
	@Override
	public List<Jobs> findAll() {
		Query<Jobs> query = session.createQuery("from Jobs", Jobs.class);
		return query.list();
	}


}
