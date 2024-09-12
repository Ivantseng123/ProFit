//package com.ProFit.dao.jobsCRUD.impl;
//
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import com.ProFit.bean.JobsApplicationProject;
//import com.ProFit.dao.jobsCRUD.AbstractGenericDAO;
//
//public class JobsApplicationProjectDAO extends AbstractGenericDAO<JobsApplicationProject, Integer> {
//    @Override
//    protected String getTableName() {
//        return "jobs_application_project";
//    }
//    @Override
//    protected String getInsertColumns() {
//    return "jobs_application_id, jobs_application_status, jobs_project, jobs_amount";
//    }
////    @Override
////    protected String getInsertPlaceholders() {
////        return CountProperty.getPropertyCount(JobsApplicationProject.class);
////    }
//    @Override
//    protected JobsApplicationProject mapRowToObject(ResultSet rs) throws SQLException {
//    	JobsApplicationProject jobsApplicationProject = new JobsApplicationProject();
//        jobsApplicationProject.setJobsApplicationProjectId(rs.getInt("jobs_application_project_id"));
//        jobsApplicationProject.setJobsApplicationId(rs.getInt("jobs_application_id"));
//        jobsApplicationProject.setJobsApplicationStatus(rs.getByte("jobs_application_status"));
//        jobsApplicationProject.setJobsProject(rs.getString("jobs_project"));
//        jobsApplicationProject.setJobsAmount(rs.getInt("jobs_amount"));
//
//
//        return jobsApplicationProject;
//    }
//    @Override
//    protected void setInsertStatementParameters(PreparedStatement stmt, JobsApplicationProject entity) throws SQLException {
//        stmt.setInt(1, entity.getJobsApplicationId());
//        stmt.setByte(2, entity.getJobsApplicationStatus());
//        stmt.setString(3, entity.getJobsProject());
//        stmt.setObject(4, entity.getJobsAmount());//原本是int
//
//    }
//    @Override
//    protected void setUpdateStatementParameters(PreparedStatement stmt, Map<String, Object> updates) throws SQLException {
//        int index = 1;
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            stmt.setObject(index++, entry.getValue());
//        }
//    }
//}
