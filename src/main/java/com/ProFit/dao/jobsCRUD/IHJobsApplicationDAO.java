package com.ProFit.dao.jobsCRUD;


import com.ProFit.bean.jobsBean.JobsApplication;

import java.util.List;

public interface IHJobsApplicationDAO {

    // 新增
    public JobsApplication save(JobsApplication jobsApplication);

    // 刪除
    public boolean delete(Integer id);

    // 更新
    public boolean update(JobsApplication jobsApplication);

    // id查詢
    public JobsApplication findById(Integer id);

    // 查詢全部
    public List<JobsApplication> findAll();



}
