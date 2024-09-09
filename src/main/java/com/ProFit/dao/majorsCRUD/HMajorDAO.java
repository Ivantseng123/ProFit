package com.ProFit.dao.majorsCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.majorsBean.MajorBean;

public class HMajorDAO {

	private Session session;

	public HMajorDAO(Session session) {
		this.session = session;
	}

	// 新增 Major
	public MajorBean insertMajor(MajorBean major) {
		session.persist(major);
		return major;
	}

	// 更新 Major
	public boolean updateMajor(MajorBean major) {
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
			oldMajor.setMajorDescription(major.getMajorDescription() == null || major.getMajorDescription().isEmpty()
					? oldMajor.getMajorDescription()
					: major.getMajorDescription());
			return true;
		}

	}

	// 删除 Major(by majorid)
	public boolean deleteMajor(int majorId) {
		MajorBean resultBean = session.get(MajorBean.class, majorId);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 查找 Major(by majorid)
	public MajorBean findMajorById(int majorId) {
		return session.get(MajorBean.class, majorId);
	}

	// 查找所有 Major
	public List<MajorBean> findAllMajors() {
		Query<MajorBean> query = session.createQuery("from MajorBean", MajorBean.class);
		return query.list();

	}

	// 根據 majorCategoryid 查找 Majors (
	public List<MajorBean> findMajorsByCategoryId(int majorCategoryId) {
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
