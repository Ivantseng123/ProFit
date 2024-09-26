package com.ProFit.dao.jobsCRUD;



import com.ProFit.bean.jobsBean.JobsApplicationProject;

import java.util.List;

public interface IHJobsApplicationProjectDAO {

    // 新增
    public JobsApplicationProject save(JobsApplicationProject jobsApplicationProject);

    // 刪除
    public boolean delete(Integer id);

    // 更新
    public boolean update(JobsApplicationProject jobsApplicationProject);

    // id查詢
    public JobsApplicationProject findById(Integer id);

    // 查詢全部
    public List<JobsApplicationProject> findAll();



}
