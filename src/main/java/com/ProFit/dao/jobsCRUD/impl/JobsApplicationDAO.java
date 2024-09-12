//package com.ProFit.dao.jobsCRUD.impl;
//
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import com.ProFit.bean.JobsApplication;
//import com.ProFit.dao.jobsCRUD.AbstractGenericDAO;
//
//public class JobsApplicationDAO extends AbstractGenericDAO<JobsApplication, Integer> {
//    @Override
//    protected String getTableName() {
//        return "jobs_application";
//    }
//    @Override
//    protected String getInsertColumns() {
//    return "jobs_application_posting_id, jobs_application_member_id, jobs_application_date, jobs_application_status, jobs_application_contract";
//    }
//    //@Override
////    protected String getInsertPlaceholders() {
////        return CountProperty.getPropertyCount(JobsApplication.class);//
////    }
//    @Override
//    protected JobsApplication mapRowToObject(ResultSet rs) throws SQLException {
//    	JobsApplication jobsApplication = new JobsApplication();
//        jobsApplication.setJobsApplicationId(rs.getInt("jobs_application_id"));
//        jobsApplication.setJobsApplicationPostingId(rs.getInt("jobs_application_posting_id"));
//        jobsApplication.setJobsApplicationMemberId(rs.getInt("jobs_application_member_id"));
//        jobsApplication.setJobsApplicationDate(rs.getDate("jobs_application_date"));
//        jobsApplication.setJobsApplicationStatus(rs.getByte("jobs_application_status"));
//        jobsApplication.setJobsApplicationContract(rs.getBlob("jobs_application_contract"));
////
//
//        return jobsApplication;
//    }
//    @Override
//    protected void setInsertStatementParameters(PreparedStatement stmt, JobsApplication entity) throws SQLException {
//        stmt.setInt(1, entity.getJobsApplicationPostingId());
//        stmt.setInt(2, entity.getJobsApplicationMemberId());
//        stmt.setDate(3, entity.getJobsApplicationDate());
//        stmt.setByte(4, entity.getJobsApplicationStatus());
//        stmt.setBlob(5, entity.getJobsApplicationContract());
//
//      //int 是 Java 中的基本數據類型。基本數據類型不能持有 null 值。int 的默認值是 0。
//        //數據庫中的 INTEGER 類型通常可以接受 NULL 值（除非被明確聲明為 NOT NULL）。
//        //使用 Java 的包裝類 Integer，並使用 setObject() 方法代替 setInt()
//    }
//    @Override
//    protected void setUpdateStatementParameters(PreparedStatement stmt, Map<String, Object> updates) throws SQLException {
//        int index = 1;
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            stmt.setObject(index++, entry.getValue());
//        }
//    }
//}
