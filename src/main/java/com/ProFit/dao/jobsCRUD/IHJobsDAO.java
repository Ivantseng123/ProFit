package com.ProFit.dao.jobsCRUD;


import java.util.List;

import com.ProFit.bean.jobsBean.Jobs;
//@Repository
public interface IHJobsDAO {

    // 新增
    public Jobs save(Jobs jobs);

    // 刪除
    public boolean delete(Integer jobsId);

    // 更新
    public boolean update(Jobs jobs);

    // id查詢
    public Jobs findById(Integer jobsId);

    // 查詢全部
    public List<Jobs> findAll();



}
