package com.ProFit.dao.majorsCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ProFit.bean.majorsBean.MajorCategoryBean;

public class MajorCategoryDAO {

	private Connection getConnection() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/ProFitDB");
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

    // 插入 MajorCategory
    public boolean insertMajorCategory(MajorCategoryBean majorCategory) {
        String sql = "INSERT INTO [profitDB].[dbo].[major_category] (major_category_id, category_name) VALUES (?, ?)";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorCategory.getMajorCategoryId());
            ps.setString(2, majorCategory.getCategoryName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新 MajorCategory
    public boolean updateMajorCategory(MajorCategoryBean majorCategory) {
        String sql = "UPDATE [profitDB].[dbo].[major_category] SET category_name = ? WHERE major_category_id = ?";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, majorCategory.getCategoryName());
            ps.setInt(2, majorCategory.getMajorCategoryId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除 MajorCategory
    public boolean deleteMajorCategory(int majorCategoryId) {
        String sql = "DELETE FROM [profitDB].[dbo].[major_category] WHERE major_category_id = ?";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorCategoryId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 查找 MajorCategory
    public MajorCategoryBean findMajorCategoryById(int majorCategoryId) {
        String sql = "SELECT * FROM [profitDB].[dbo].[major_category] WHERE major_category_id = ?";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorCategoryId);
            try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
				    MajorCategoryBean majorCategory = new MajorCategoryBean();
				    majorCategory.setMajorCategoryId(rs.getInt("major_category_id"));
				    majorCategory.setCategoryName(rs.getString("category_name"));
				    return majorCategory;
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查找所有 MajorCategory
    public List<MajorCategoryBean> findAllMajorCategories() {
        String sql = "SELECT * FROM [profitDB].[dbo].[major_category]";
        List<MajorCategoryBean> majorCategoryList = new ArrayList<>();
        try (Connection connection = getConnection();
        		Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
            	MajorCategoryBean majorCategory = new MajorCategoryBean();
                majorCategory.setMajorCategoryId(rs.getInt("major_category_id"));
                majorCategory.setCategoryName(rs.getString("category_name"));
                majorCategoryList.add(majorCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majorCategoryList;
    }
}