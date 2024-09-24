package com.ProFit.dao.majorsDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorBean;

@Repository
@Transactional
public class HMajorDAO implements IHmajorDAO {

	@Autowired
	private SessionFactory factory;

	// 新增 Major
	@Override
	public MajorBean insertMajor(MajorBean major) {
		Session session = factory.getCurrentSession();
		session.persist(major);
		return major;
	}

	// 更新 Major
	@Override
	public boolean updateMajor(MajorBean major) {
		Session session = factory.getCurrentSession();
		MajorBean oldMajor = session.get(MajorBean.class, major.getMajorId());
		if (oldMajor == null) {
			System.out.println("major with id:" + major.getMajorId() + "does not exist.");
			return false;
		} else {
			oldMajor.setMajorName(
					major.getMajorName() == null || major.getMajorName().isEmpty() ? oldMajor.getMajorName()
							: major.getMajorName());
			oldMajor.setMajorCategoryId(
					major.getMajorCategoryId() == null ? oldMajor.getMajorCategoryId() : major.getMajorCategoryId());
			oldMajor.setMajorDescription(major.getMajorDescription());
			return true;
		}

	}

	// 删除 Major(by majorid)
	@Override
	public boolean deleteMajor(int majorId) {
		Session session = factory.getCurrentSession();
		MajorBean resultBean = session.get(MajorBean.class, majorId);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 查找 Major(by majorid)
	@Override
	public MajorBean findMajorById(int majorId) {
		Session session = factory.getCurrentSession();
		return session.get(MajorBean.class, majorId);
	}

	// 查找所有 Major
	@Override
	public List<MajorBean> findAllMajors() {
		Session session = factory.getCurrentSession();
		Query<MajorBean> query = session.createQuery("from MajorBean", MajorBean.class);
		return query.list();
	}

	// 根據 majorCategoryid 查找 Majors (
	@Override
	public List<MajorBean> findMajorsByCategoryId(int majorCategoryId) {
		Session session = factory.getCurrentSession();
		String hql = "from MajorBean m WHERE m.majorCategoryId = :categoryId";
		Query<MajorBean> query = session.createQuery(hql, MajorBean.class);
		query.setParameter("categoryId", majorCategoryId);

		// 查詢結果
		List<MajorBean> queryResults = query.list();
		return queryResults;
	}

	// 根據categoryId查找CategoryName (可以棄用了)
	/*
	 * public String getCategoryNameById(int categoryId) throws SQLException {
	 * String sql =
	 * "SELECT category_name FROM [profitDB].[dbo].[major_category] WHERE major_category_id = ?"
	 * ; try (Connection connection = getConnection(); PreparedStatement ps =
	 * connection.prepareStatement(sql)) { ps.setInt(1, categoryId); try (ResultSet
	 * rs = ps.executeQuery()) { if (rs.next()) { return
	 * rs.getString("category_name"); } } } return "Unknown Category"; }
	 */

}
