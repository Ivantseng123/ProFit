package com.ProFit.jobService;

import java.util.List;
import java.util.Map;

import com.ProFit.bean.Jobs;
import com.ProFit.dao.jobsCRUD.impl.JobsDAO;

public class JobsService {

    private final JobsDAO jobsDAO = new JobsDAO();

    public int save(Jobs jobs) {
        return jobsDAO.save(jobs);
    }

    public Jobs findById(Integer jobsId) {
        return jobsDAO.findById(jobsId);
    }

    public List<Jobs> findAll() {
        return jobsDAO.findAll();
    }

    public void update(Integer jobsId, Map<String, Object> updates) { //String是指欄位名稱,Object是指欄位值
    	jobsDAO.update(jobsId, updates);
    }

    public void delete(Integer jobsId) {
//        if (id == 1){
//            System.out.println("不能刪除");
//        } else {
    	jobsDAO.delete(jobsId);
//        }
    }
}
