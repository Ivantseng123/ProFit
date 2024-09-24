package com.ProFit.service.majorService;

import java.util.List;

import com.ProFit.bean.majorsBean.MajorBean;

public interface IMajorService {

	// 新增 Major
	MajorBean insertMajor(MajorBean major);

	// 更新 Major
	boolean updateMajor(MajorBean major);

	// 删除 Major(by majorid)
	boolean deleteMajor(int majorId);

	// 查找 Major(by majorid)
	MajorBean findMajorById(int majorId);

	// 查找所有 Major
	List<MajorBean> findAllMajors();

	// 根據 majorCategoryid 查找 Majors (
	List<MajorBean> findMajorsByCategoryId(int majorCategoryId);

}