//package com.ProFit.jobService;
//
//import java.util.List;
//import java.util.Map;
//
//import com.ProFit.bean.JobsApplication;
//import com.ProFit.dao.jobsCRUD.impl.JobsApplicationDAO;
//
//
//public class JobsApplicationService {
//	private final JobsApplicationDAO jobsApplicationDAO = new JobsApplicationDAO();
//
//    public int save(JobsApplication jobsApplication) {
//        return jobsApplicationDAO.save(jobsApplication);
//    }
//
//    public JobsApplication findById(Integer jobsApplicationId) {
//        return jobsApplicationDAO.findById(jobsApplicationId);
//    }
//
//    public List<JobsApplication> findAll() {
//        return jobsApplicationDAO.findAll();
//    }
//
//    public void update(Integer jobsApplicationId, Map<String, Object> updates) { //String是指欄位名稱,Object是指欄位值
//    	jobsApplicationDAO.update(jobsApplicationId, updates);
//    }
//
//    public void delete(Integer jobsApplicationId) {
////        if (id == 1){
////            System.out.println("不能刪除");
////        } else {
//    	jobsApplicationDAO.delete(jobsApplicationId);
////        }
//    }
//
//
//}
