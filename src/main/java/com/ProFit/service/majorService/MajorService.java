package com.ProFit.service.majorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.dao.majorsDao.IHMajorDAO;

@Service
@Transactional
public class MajorService implements IMajorService {

	@Autowired
	private IHMajorDAO majorDAO;

	// 新增 Major
	@Override
	public MajorBean insertMajor(MajorBean major) {
		return majorDAO.insertMajor(major);
	}

	// 更新 Major
	@Override
	public boolean updateMajor(MajorBean major) {
		return majorDAO.updateMajor(major);
	}

	// 删除 Major(by majorid)
	@Override
	public boolean deleteMajor(int majorId) {
		return majorDAO.deleteMajor(majorId);
	}

	// 查找 Major(by majorid)
	@Override
	public MajorBean findMajorById(int majorId) {
		return majorDAO.findMajorById(majorId);
	}

	// 查找所有 Major
	@Override
	public List<MajorBean> findAllMajors() {
		List<MajorBean> listMajor = majorDAO.findAllMajors();
		// 強制初始化延遲加載的屬性
		for (MajorBean major : listMajor) {
			// 訪問屬性，觸發加載
			major.getMajorCategory().getCategoryName();
        }
		return listMajor;
	}

	// 根據 majorCategoryid 查找 Majors (
	@Override
	public List<MajorBean> findMajorsByCategoryId(int majorCategoryId) {
		List<MajorBean> listMajor = majorDAO.findAllMajors();
		// 強制初始化延遲加載的屬性
		for (MajorBean major : listMajor) {
			// 訪問屬性，觸發加載
			major.getMajorCategory().getCategoryName();
        }
		return majorDAO.findMajorsByCategoryId(majorCategoryId);
	}

}
