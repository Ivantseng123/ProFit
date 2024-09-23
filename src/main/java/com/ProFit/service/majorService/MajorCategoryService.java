package com.ProFit.service.majorService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorCategoryBean;
import com.ProFit.dao.majorsDao.IHmahorCategoryDAO;

@Service
@Transactional
public class MajorCategoryService implements IMajorCategoryService {

	@Autowired
	private IHmahorCategoryDAO majorCategoryDAO;

	// 新增專業類別
	@Override
	public MajorCategoryBean insertMajorCategory(MajorCategoryBean majorCategory) {
		return majorCategoryDAO.insertMajorCategory(majorCategory);
	}

	// 更新專業類別
	@Override
	public boolean updateMajorCategory(MajorCategoryBean majorCategory) {
		return majorCategoryDAO.updateMajorCategory(majorCategory);
	}

	// 刪除專業類別
	@Override
	public boolean deleteMajorCategory(int majorCategoryId) {
		return majorCategoryDAO.deleteMajorCategory(majorCategoryId);
	}

	// 查詢所有專業類別
	@Override
	public List<MajorCategoryBean> findAllMajorCategories() {
		return majorCategoryDAO.findAllMajorCategories();
	}

	// 根據ID查詢專業類別
	@Override
	public MajorCategoryBean findMajorCategoryById(int majorCategoryId) {
		return majorCategoryDAO.findMajorCategoryById(majorCategoryId);
	}
}