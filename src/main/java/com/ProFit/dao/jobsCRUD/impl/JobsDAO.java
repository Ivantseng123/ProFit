//package com.ProFit.dao.jobsCRUD.impl;
//
//
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import com.ProFit.bean.Jobs;
//import com.ProFit.dao.jobsCRUD.AbstractGenericDAO;
//
//public class JobsDAO extends AbstractGenericDAO<Jobs, Integer> {
//	
//	
//    @Override
//    protected String getTableName() {
//        return "jobs";
//    }
//    
//  
//    @Override
//    protected String getInsertColumns() {
//    return "jobs_user_id, jobs_title, jobs_posting_date, jobs_application_deadline, jobs_description, jobs_status, jobs_required_skills, jobs_location, jobs_max_salary, jobs_min_salary, jobs_worktime, jobs_number_of_openings";
//    }
////    @Override
////    protected String getInsertPlaceholders() {
////        return CountProperty.getPropertyCount(JobsApplicationProject.class);
////    }
//    
//    
//    @Override
//    protected Jobs mapRowToObject(ResultSet rs) throws SQLException {
//    	Jobs jobs = new Jobs();
//        jobs.setJobsId(rs.getInt("jobs_id"));
//        jobs.setJobsUserId(rs.getInt("jobs_user_id"));
//        jobs.setJobsTitle(rs.getString("jobs_title"));
//        jobs.setJobsPostingDate(rs.getDate("jobs_posting_date"));
//        jobs.setJobsApplicationDeadline(rs.getDate("jobs_application_deadline"));
//        jobs.setJobsDescription(rs.getString("jobs_description"));
//        jobs.setJobsStatus(rs.getByte("jobs_status"));
//        jobs.setJobsRequiredSkills(rs.getString("jobs_required_skills"));
//        jobs.setJobsLocation(rs.getString("jobs_location"));
//        jobs.setJobsMaxSalary(rs.getInt("jobs_max_salary"));
//        jobs.setJobsMinSalary(rs.getInt("jobs_min_salary"));
//        jobs.setJobsWorktime(rs.getString("jobs_worktime"));
//        jobs.setJobsNumberOfOpenings(rs.getInt("jobs_number_of_openings"));
//        return jobs;
//    }
//    @Override
//    protected void setInsertStatementParameters(PreparedStatement stmt, Jobs entity) throws SQLException {
//        stmt.setInt(1, entity.getJobsUserId());
//        stmt.setString(2, entity.getJobsTitle());
//        stmt.setDate(3, (Date) entity.getJobsPostingDate());
//        stmt.setDate(4, (Date) entity.getJobsApplicationDeadline());
//        stmt.setString(5, entity.getJobsDescription());
//        stmt.setByte(6, entity.getJobsStatus());
//        stmt.setString(7, entity.getJobsRequiredSkills());
//        stmt.setString(8, entity.getJobsLocation());
//        stmt.setObject(9, entity.getJobsMaxSalary());//原本是int
//        stmt.setObject(10, entity.getJobsMinSalary());//原本是int
//        stmt.setString(11, entity.getJobsWorktime());
//        stmt.setObject(12, entity.getJobsNumberOfOpenings());//原本是int
//
//        //int 是 Java 中的基本數據類型。基本數據類型不能持有 null 值。int 的默認值是 0。
//        //數據庫中的 INTEGER 類型通常可以接受 NULL 值（除非被明確聲明為 NOT NULL）。
//        //使用 Java 的包裝類 Integer，並使用 setObject() 方法代替 setInt()。
//    }
//    @Override
//    protected void setUpdateStatementParameters(PreparedStatement stmt, Map<String, Object> updates) throws SQLException {
//        int index = 1;
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            stmt.setObject(index++, entry.getValue());
//        }
//    }
//
//}
