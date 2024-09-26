package com.ProFit.service.jobService;

import java.util.List;

import com.ProFit.bean.jobsBean.Jobs;
import com.ProFit.dao.jobsCRUD.IHJobsDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobsService implements IJobsService{

	private final IHJobsDAO jobsDAO;

    public JobsService(IHJobsDAO jobsDAO) {
        this.jobsDAO = jobsDAO;
    }

    @Override
    public Jobs save(Jobs jobs) {
        return jobsDAO.save(jobs);
    }

    @Override
    public Jobs findById(Integer jobsId) {
        return jobsDAO.findById(jobsId);
    }

    @Override
    public List<Jobs> findAll() {
        return jobsDAO.findAll();
    }

    @Override
    public void update(Jobs jobs) { //String是指欄位名稱,Object是指欄位值
    	jobsDAO.update(jobs);
    }

    @Override
    public void delete(Integer jobsId) {
//        if (id == 1){
//            System.out.println("不能刪除");
//        } else {
    	jobsDAO.delete(jobsId);
//        }
    }
}
