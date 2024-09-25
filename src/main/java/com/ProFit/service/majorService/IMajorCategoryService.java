package com.ProFit.service.majorService;

import java.util.List;

import com.ProFit.bean.majorsBean.MajorCategoryBean;

public interface IMajorCategoryService {

	// 新增專業類別
	MajorCategoryBean insertMajorCategory(MajorCategoryBean majorCategory);

	// 更新專業類別
	boolean updateMajorCategory(MajorCategoryBean majorCategory);

	// 刪除專業類別
	boolean deleteMajorCategory(int majorCategoryId);

	// 查詢所有專業類別
	List<MajorCategoryBean> findAllMajorCategories();

	// 根據ID查詢專業類別
	MajorCategoryBean findMajorCategoryById(int majorCategoryId);

}