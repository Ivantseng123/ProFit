package com.ProFit.dao.usersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProFit.bean.usersBean.Pwd_reset_tokens;
import com.ProFit.bean.usersBean.Users;

public class PwdResetTokensDao {
	public void saveTokensInfo(Pwd_reset_tokens token) {
		String sql = "INSERT INTO password_reset_tokens(user_id,user_tokenHash) VALUES(?,?)";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1,token.getUser_id());
			statement.setString(2, token.getUser_tokenHash());
		
			statement.execute();
			System.out.println("新增完成");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void deleteTokensInfo(int token_id) {
		String sql = "DELETE FROM password_reset_tokens WHERE token_id = ?";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1,token_id);
			
			statement.execute();
			System.out.println("刪除成功");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public List<Pwd_reset_tokens> getAllTokensInfo(){
		String sql = "SELECT p.token_id, p.user_id, u.user_email, p.user_tokenHash, p.expiration_time FROM password_reset_tokens AS p "
				+ "JOIN users AS u ON p.user_id = u.user_id";
		List<Pwd_reset_tokens> tokens = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();){
			
			Pwd_reset_tokens token = null;
			while(rs.next()) {
				token = new Pwd_reset_tokens();
				token.setToken_id(rs.getInt("token_id"));
				token.setUser_id(rs.getInt("user_id"));
				token.setUser_email(rs.getString("user_email"));
				token.setUser_tokenHash(rs.getString("user_tokenHash"));
				token.setExpiration_time(rs.getString("expiration_time"));
				tokens.add(token);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tokens;		
	};
	
	public List<Pwd_reset_tokens> searchTokensInfo(String email){
		String sql = "SELECT p.token_id, p.user_id, u.user_email, p.user_tokenHash, p.expiration_time FROM password_reset_tokens AS p "
				+ "JOIN users AS u ON p.user_id = u.user_id WHERE u.user_email LIKE ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Pwd_reset_tokens> tokens = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection()){
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + email + "%");	
			rs = statement.executeQuery();
			
			if (rs.next() == false) {
				System.out.println("找不到");
			}
			
			Pwd_reset_tokens token = null;
			while(rs.next()) {
				token = new Pwd_reset_tokens();
				token.setToken_id(rs.getInt("token_id"));
				token.setUser_id(rs.getInt("user_id"));
				token.setUser_email(rs.getString("user_email"));
				token.setUser_tokenHash(rs.getString("user_tokenHash"));
				token.setExpiration_time(rs.getString("expiration_time"));
				tokens.add(token);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tokens;		
	};
}
