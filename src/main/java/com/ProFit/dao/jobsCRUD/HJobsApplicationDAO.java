package com.ProFit.dao.jobsCRUD;

import java.util.List;

import com.ProFit.bean.JobsApplication;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HJobsApplicationDAO implements IHJobsApplicationDAO {

    // session設置
    private Session session;

    public HJobsApplicationDAO(Session session) {
        this.session = session;
    }

    // 新增
    @Override
    public JobsApplication save(JobsApplication jobsApplication) {
        session.persist(jobsApplication);
        return jobsApplication;
    }

    // 刪除
    @Override
    public boolean delete(Integer jobsApplicationId) {
        JobsApplication jobsApplication = session.get(JobsApplication.class, jobsApplicationId);
        if (jobsApplication != null) {
            session.remove(jobsApplication);
            return true;
        }
        return false;
    }

    // 修改
    @Override
    public boolean update(JobsApplication jobsApplication) {
        JobsApplication oldJobsApplication = session.get(JobsApplication.class, jobsApplication.getJobsApplicationId());

        if (oldJobsApplication == null) {
            System.out.println("JobsApplication with ID " + jobsApplication.getJobsApplicationId() + " does not exist.");
            return false;
        }

        // 對比屬性值，並進行更新操作
        oldJobsApplication.setPoster(jobsApplication.getPoster() == null
                ? oldJobsApplication.getPoster()
                : jobsApplication.getPoster());

        oldJobsApplication.setApplicant(jobsApplication.getApplicant() == null
                ? oldJobsApplication.getApplicant()
                : jobsApplication.getApplicant());

        oldJobsApplication.setJobsApplicationDate(jobsApplication.getJobsApplicationDate() == null
                ? oldJobsApplication.getJobsApplicationDate()
                : jobsApplication.getJobsApplicationDate());

        oldJobsApplication.setJobsApplicationStatus(jobsApplication.getJobsApplicationStatus() == null
                ? oldJobsApplication.getJobsApplicationStatus()
                : jobsApplication.getJobsApplicationStatus());

        oldJobsApplication.setJobsApplicationContract(jobsApplication.getJobsApplicationContract() == null
                ? oldJobsApplication.getJobsApplicationContract()
                : jobsApplication.getJobsApplicationContract());

        session.merge(oldJobsApplication);
        return true;
    }

    // id查詢
    @Override
    public JobsApplication findById(Integer jobsApplicationId) {
        return session.get(JobsApplication.class, jobsApplicationId);
    }

    // 查詢全部
    @Override
    public List<JobsApplication> findAll() {
        Query<JobsApplication> query = session.createQuery("from JobsApplication", JobsApplication.class);
        return query.list();
    }
}