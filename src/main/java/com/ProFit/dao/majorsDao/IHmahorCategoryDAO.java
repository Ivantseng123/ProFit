package com.ProFit.dao.majorsDao;

import java.util.List;

import com.ProFit.bean.majorsBean.MajorCategoryBean;

public interface IHmahorCategoryDAO {

	// 新增 MajorCategory
	MajorCategoryBean insertMajorCategory(MajorCategoryBean majorCategory);

	// 修改 MajorCategory(的name by id)
	boolean updateMajorCategory(MajorCategoryBean newMajorCategory);

	// 刪除 MajorCategory(by id)
	boolean deleteMajorCategory(int majorCategoryId);

	// 查詢全部 MajorCategory
	List<MajorCategoryBean> findAllMajorCategories();

	// 查詢單筆 MajorCategory(by id)
	MajorCategoryBean findMajorCategoryById(int majorCategoryId);

}