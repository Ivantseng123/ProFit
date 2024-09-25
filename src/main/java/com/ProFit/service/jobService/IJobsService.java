package com.ProFit.service.jobService;


import com.ProFit.bean.jobsBean.Jobs;

import java.util.List;

public interface IJobsService {
    Jobs save(Jobs jobs);

    Jobs findById(Integer jobsId);

    List<Jobs> findAll();

    void update(Jobs jobs);

    void delete(Integer jobsId);
}
