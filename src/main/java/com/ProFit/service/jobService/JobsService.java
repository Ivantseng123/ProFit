package com.ProFit.service.jobService;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.ProFit.bean.Jobs;
import com.ProFit.dao.jobsCRUD.HJobsDAO;
import com.ProFit.dao.jobsCRUD.IHJobsDAO;
//@Service
public class JobsService {
	
	private Session session;
	private final IHJobsDAO jobsDAO;
	
	
    public JobsService(Session session) {
		super();
		this.session = session;
		jobsDAO = new HJobsDAO(session);
	}

	

    public Jobs save(Jobs jobs) {
        return jobsDAO.save(jobs);
    }

    public Jobs findById(Integer jobsId) {
        return jobsDAO.findById(jobsId);
    }

    public List<Jobs> findAll() {
        return jobsDAO.findAll();
    }

    public void update(Jobs jobs) { //String是指欄位名稱,Object是指欄位值
    	jobsDAO.update(jobs);
    }

    public void delete(Integer jobsId) {
//        if (id == 1){
//            System.out.println("不能刪除");
//        } else {
    	jobsDAO.delete(jobsId);
//        }
    }
}
