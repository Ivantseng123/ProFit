package com.ProFit.dao.majorsCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ProFit.bean.majorsBean.UserMajorBean;

public class UserMajorDAO {

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

    // 插入 UserMajor
    public boolean insertUserMajor(UserMajorBean userMajor) {
        String sql = "INSERT INTO [profitDB].[dbo].[user_major] (user_id, major_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userMajor.getUserId());
            ps.setInt(2, userMajor.getMajorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除 UserMajor
    public boolean deleteUserMajor(int userId, int majorId) {
        String sql = "DELETE FROM [profitDB].[dbo].[user_major] WHERE user_id = ? AND major_id = ?";
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, majorId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> getAllUsers() throws SQLException {
        Map<String, String> users = new HashMap<>();
        String sql = "SELECT user_id, user_name FROM [profitDB].[dbo].[users]";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.put(rs.getString("user_name"), rs.getString("user_name"));
            }
        }
        return users;
    }

    public Map<String, String> getAllMajors() throws SQLException {
        Map<String, String> majors = new HashMap<>();
        String sql = "SELECT major_id, major_name FROM [profitDB].[dbo].[major]";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                majors.put(rs.getString("major_name"), rs.getString("major_name"));
            }
        }
        return majors;
    }



    // 查找特定user的所有 Major
    public List<UserMajorBean> findMajorsByUserId(int userId) {
        String sql = "SELECT * FROM [profitDB].[dbo].[user_major] WHERE user_id = ?";
        List<UserMajorBean> userMajorList = new ArrayList<>();
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
				    UserMajorBean userMajor = new UserMajorBean();
				    userMajor.setUserId(rs.getInt("user_id"));
				    userMajor.setMajorId(rs.getInt("major_id"));
				    userMajorList.add(userMajor);
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMajorList;
    }

    // 查找特定 Major 的所有 User
    public List<UserMajorBean> findUsersByMajorId(int majorId) {
        String sql = "SELECT * FROM [profitDB].[dbo].[user_major] WHERE major_id = ?";
        List<UserMajorBean> userMajorList = new ArrayList<>();
        try (Connection connection = getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
				    UserMajorBean userMajor = new UserMajorBean();
				    userMajor.setUserId(rs.getInt("user_id"));
				    userMajor.setMajorId(rs.getInt("major_id"));
				    userMajorList.add(userMajor);
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMajorList;
    }

    public int getUserIdByName(String userName) throws SQLException {
        String sql = "SELECT user_id FROM [profitDB].[dbo].[users] WHERE user_name = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        }
        return -1; // 如果找不到用戶
    }

    public int getMajorIdByName(String majorName) throws SQLException {
        String sql = "SELECT major_id FROM [profitDB].[dbo].[major] WHERE major_name = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, majorName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("major_id");
                }
            }
        }
        return -1; // 如果找不到專業
    }

    // 查找所有 UserMajor
    public List<UserMajorBean> findAllUserMajors() {
        String sql = "SELECT * FROM [profitDB].[dbo].[user_major]";
        List<UserMajorBean> userMajorList = new ArrayList<>();
        try (Connection connection = getConnection();
        		Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UserMajorBean userMajor = new UserMajorBean();
                userMajor.setUserId(rs.getInt("user_id"));
                userMajor.setMajorId(rs.getInt("major_id"));
                userMajorList.add(userMajor);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return userMajorList;
    }

    //
    public String getUserNameById(int userId) throws SQLException {
        String sql = "SELECT user_name FROM [profitDB].[dbo].[users] WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("user_name");
                }
            }
        }
        return "Unknown User";
    }

    public String getMajorNameById(int majorId) throws SQLException {
        String sql = "SELECT major_name FROM [profitDB].[dbo].[major] WHERE major_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("major_name");
                }
            }
        }
        return "Unknown Major";
    }
}
