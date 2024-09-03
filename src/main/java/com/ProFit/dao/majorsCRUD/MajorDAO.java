package com.ProFit.dao.majorsCRUD;

import com.ProFit.bean.MajorBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MajorDAO {
	
	
	public MajorDAO() {
		super();
	}

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
	
    // 插入 Major 
    public boolean insertMajor(MajorBean major) {
        String sql = "INSERT INTO [profitDB].[dbo].[major] (major_id, major_name, major_category_id, major_description) VALUES (?, ?, ?, ?)";
        try (Connection connection =getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, major.getMajorId());
            ps.setString(2, major.getMajorName());
            ps.setInt(3, major.getMajorCategoryId());
            ps.setString(4, major.getMajorDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新 Major 
    public boolean updateMajor(MajorBean major) {
        String sql = "UPDATE [profitDB].[dbo].[major] SET major_name = ?, major_category_id = ?, major_description = ? WHERE major_id = ?";
        try (Connection connection =getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, major.getMajorName());
            ps.setInt(2, major.getMajorCategoryId());
            ps.setString(3, major.getMajorDescription());
            ps.setInt(4, major.getMajorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除 Major 
    public boolean deleteMajor(int majorId) {
        String sql = "DELETE FROM [profitDB].[dbo].[major] WHERE major_id = ?";
        try (Connection connection =getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 查找 Major 
    public MajorBean findMajorById(int majorId) {
        String sql = "SELECT * FROM [profitDB].[dbo].[major] WHERE major_id = ?";
        try (Connection connection =getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
				    MajorBean major = new MajorBean();
				    major.setMajorId(rs.getInt("major_id"));
				    major.setMajorName(rs.getString("major_name"));
				    major.setMajorCategoryId(rs.getInt("major_category_id"));
				    major.setMajorDescription(rs.getString("major_description"));
				    return major;
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查找所有 Major 
    public List<MajorBean> findAllMajors() {
        String sql = "SELECT * FROM [profitDB].[dbo].[major]";
        List<MajorBean> majorList = new ArrayList<>();
        try (Connection connection =getConnection();
        		Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MajorBean major = new MajorBean();
                major.setMajorId(rs.getInt("major_id"));
                major.setMajorName(rs.getString("major_name"));
                major.setMajorCategoryId(rs.getInt("major_category_id"));
                major.setMajorDescription(rs.getString("major_description"));
                majorList.add(major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majorList;
    }
    
    //根據majorCategory查找
    public List<MajorBean> findMajorsByCategoryId(int majorCategoryId) {
        String sql = "SELECT * FROM [profitDB].[dbo].[major] WHERE major_category_id = ?";
        List<MajorBean> majorList = new ArrayList<>();
        try (Connection connection =getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorCategoryId);
            try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
				    MajorBean major = new MajorBean();
				    major.setMajorId(rs.getInt("major_id"));
				    major.setMajorName(rs.getString("major_name"));
				    major.setMajorCategoryId(rs.getInt("major_category_id"));
				    major.setMajorDescription(rs.getString("major_description"));
				    majorList.add(major);
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majorList;
    }
    
    //
    public String getCategoryNameById(int categoryId) throws SQLException {
        String sql = "SELECT category_name FROM [profitDB].[dbo].[major_category] WHERE major_category_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("category_name");
                }
            }
        }
        return "Unknown Category";
    }
}