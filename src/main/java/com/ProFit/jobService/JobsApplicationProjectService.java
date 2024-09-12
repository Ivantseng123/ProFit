//package com.ProFit.jobService;
//
//import java.util.List;
//import java.util.Map;
//
//import com.ProFit.bean.JobsApplicationProject;
//import com.ProFit.dao.jobsCRUD.impl.JobsApplicationProjectDAO;
//
//public class JobsApplicationProjectService {
//	private final JobsApplicationProjectDAO jobsApplicationProjectDAO = new JobsApplicationProjectDAO();
//
//    public int save(JobsApplicationProject jobsApplicationProject) {
//        return jobsApplicationProjectDAO.save(jobsApplicationProject);
//    }
//
//    public JobsApplicationProject findById(Integer jobsApplicationProjectId) {
//        return jobsApplicationProjectDAO.findById(jobsApplicationProjectId);
//    }
//
//    public List<JobsApplicationProject> findAll() {
//        return jobsApplicationProjectDAO.findAll();
//    }
//
//    public void update(Integer jobsApplicationProjectId, Map<String, Object> updates) { //String是指欄位名稱,Object是指欄位值
//    	jobsApplicationProjectDAO.update(jobsApplicationProjectId, updates);
//    }
//
//    public void delete(Integer jobsApplicationProjectId) {
////        if (id == 1){
////            System.out.println("不能刪除");
////        } else {
//    	jobsApplicationProjectDAO.delete(jobsApplicationProjectId);
////        }
//    }
//
//
//}
