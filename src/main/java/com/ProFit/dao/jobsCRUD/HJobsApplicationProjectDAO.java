package com.ProFit.dao.jobsCRUD;

import java.util.List;

import com.ProFit.bean.JobsApplicationProject;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HJobsApplicationProjectDAO implements IHJobsApplicationProjectDAO {

    // session設置
    private Session session;

    public HJobsApplicationProjectDAO(Session session) {
        this.session = session;
    }

    // 新增
    @Override
    public JobsApplicationProject save(JobsApplicationProject jobsApplicationProject) {
        session.persist(jobsApplicationProject);
        return jobsApplicationProject;
    }

    // 刪除
    @Override
    public boolean delete(Integer jobsApplicationProjectId) {
        JobsApplicationProject jobsApplicationProject = session.get(JobsApplicationProject.class, jobsApplicationProjectId);
        if (jobsApplicationProject != null) {
            session.remove(jobsApplicationProject);
            return true;
        }
        return false;
    }

    // 修改
    @Override
    public boolean update(JobsApplicationProject jobsApplicationProject) {
        JobsApplicationProject oldJobsApplicationProject = session.get(JobsApplicationProject.class, jobsApplicationProject.getJobsApplicationProjectId());

        if (oldJobsApplicationProject == null) {
            System.out.println("JobsApplicationProject with ID " + jobsApplicationProject.getJobsApplicationProjectId() + " does not exist.");
            return false;
        }

        // 對比屬性值，並進行更新操作
        oldJobsApplicationProject.setJobsApplication(jobsApplicationProject.getJobsApplication() == null
                ? oldJobsApplicationProject.getJobsApplication()
                : jobsApplicationProject.getJobsApplication());

        oldJobsApplicationProject.setJobsApplicationStatus(jobsApplicationProject.getJobsApplicationStatus() == null
                ? oldJobsApplicationProject.getJobsApplicationStatus()
                : jobsApplicationProject.getJobsApplicationStatus());

        oldJobsApplicationProject.setJobsProject(jobsApplicationProject.getJobsProject() == null || jobsApplicationProject.getJobsProject().isEmpty()
                ? oldJobsApplicationProject.getJobsProject()
                : jobsApplicationProject.getJobsProject());

        oldJobsApplicationProject.setJobsAmount(jobsApplicationProject.getJobsAmount() == null
                ? oldJobsApplicationProject.getJobsAmount()
                : jobsApplicationProject.getJobsAmount());

        session.merge(oldJobsApplicationProject);
        return true;
    }

    // id查詢
    @Override
    public JobsApplicationProject findById(Integer jobsApplicationProjectId) {
        return session.get(JobsApplicationProject.class, jobsApplicationProjectId);
    }

    // 查詢全部
    @Override
    public List<JobsApplicationProject> findAll() {
        Query<JobsApplicationProject> query = session.createQuery("from JobsApplicationProject", JobsApplicationProject.class);
        return query.list();
    }
}