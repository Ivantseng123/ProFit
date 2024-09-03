package com.ProFit.dao.usersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProFit.bean.usersBean.Users;

public class UserDao {
	public void saveUserInfo(Users user) {
		String sql = "INSERT INTO users(user_name,user_email,user_passwordHash,user_phoneNumber,user_city,user_identity, freelancer_profile_status) VALUES(?,?,?,?,?,?,?)";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1,user.getUser_name());
			statement.setString(2, user.getUser_email());
			statement.setString(3, user.getUser_passwordHash());
			statement.setString(4, user.getUser_phoneNumber());
			statement.setString(5,user.getUser_city());
			statement.setInt(6, user.getUser_identity());
			statement.setInt(7, user.getFreelancer_profile_status());
			
			statement.execute();
			System.out.println("新增完成");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void deleteUserInfo(int user_id) {
		String sql = "DELETE FROM users WHERE user_id = ?";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1,user_id);
			
			statement.execute();
			System.out.println("刪除成功");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updateUserPwd(String pwd,int user_id) {
		String sql = "UPDATE users SET user_passwordHash = ? WHERE user_id = ?";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			
			statement.setString(1, pwd);
			statement.setInt(2, user_id);
			
			statement.executeUpdate();		
			System.out.println("更新成功");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserInfo(Users user) {
		String sql = "UPDATE users SET user_name = ?, user_email = ?, user_passwordHash = ?, user_phoneNumber = ?,"
				+ "user_city = ?, user_identity = ?, user_pictureURL = ?, user_balance = ?, freelancer_location_prefer = ?, freelancer_exprience = ?, freelancer_identity = ?, freelancer_profile_status = ?, freelancer_disc = ? WHERE user_id = ?";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			
			statement.setString(1, user.getUser_name());
			statement.setString(2, user.getUser_email());
			statement.setString(3, user.getUser_passwordHash());
			statement.setString(4, user.getUser_phoneNumber());
			statement.setString(5, user.getUser_city());
			statement.setInt(6, user.getUser_identity());
			statement.setString(7, user.getUser_pictureURL());
			statement.setInt(8, user.getUser_balance());
			statement.setString(9, user.getFreelancer_location_prefer());
			statement.setString(10, user.getFreelancer_exprience());
			statement.setString(11, user.getFreelancer_identity());
			statement.setInt(12, user.getFreelancer_profile_status());
			statement.setString(13, user.getFreelancer_disc());
			statement.setInt(14, user.getUser_id());
			
			statement.executeUpdate();		
			System.out.println("更新成功");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserInfo1(Users user) {
		String sql = "UPDATE users SET user_name = ?, user_email = ?, user_passwordHash = ?, user_phoneNumber = ?,"
				+ "user_city = ?, user_identity = ?, user_balance = ?, freelancer_location_prefer = ?, freelancer_exprience = ?, freelancer_identity = ?, freelancer_profile_status = ?, freelancer_disc = ? WHERE user_id = ?";
		
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			
			statement.setString(1, user.getUser_name());
			statement.setString(2, user.getUser_email());
			statement.setString(3, user.getUser_passwordHash());
			statement.setString(4, user.getUser_phoneNumber());
			statement.setString(5, user.getUser_city());
			statement.setInt(6, user.getUser_identity());
			statement.setInt(7, user.getUser_balance());
			statement.setString(8, user.getFreelancer_location_prefer());
			statement.setString(9, user.getFreelancer_exprience());
			statement.setString(10, user.getFreelancer_identity());
			statement.setInt(11, user.getFreelancer_profile_status());
			statement.setString(12, user.getFreelancer_disc());
			statement.setInt(13, user.getUser_id());
			
			statement.executeUpdate();		
			System.out.println("更新成功");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserIdentity(int user_id) {
		String sql = "UPDATE users SET user_identity = ? WHERE user_id = ?";
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
			
			statement.setInt(1, 2);
			statement.setInt(2, user_id);
	
			
			statement.executeUpdate();		
			System.out.println("更新成功");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Users> getAllUserInfo(){
		String sql = "SELECT * FROM users";
		List<Users> users = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();){
			
			Users user = null;
			while(rs.next()) {
				user = new Users();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_passwordHash(rs.getString("user_passwordHash"));
				user.setUser_phoneNumber(rs.getString("user_phoneNumber"));
				user.setUser_city(rs.getString("user_city"));
				user.setUser_identity(rs.getInt("user_identity"));
				user.setUser_register_time(rs.getString("user_register_time"));
				user.setUser_pictureURL(rs.getString("user_pictureURL"));
				user.setUser_balance(rs.getInt("user_balance"));
				user.setFreelancer_location_prefer(rs.getString("freelancer_location_prefer"));
				user.setFreelancer_exprience(rs.getString("freelancer_exprience"));
				user.setFreelancer_identity(rs.getString("freelancer_identity"));
				user.setFreelancer_profile_status(rs.getInt("freelancer_profile_status"));
				user.setFreelancer_disc(rs.getString("freelancer_disc"));
				users.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;		
	};
	
	public Users getUserInfoByID(int user_id){
		String sql = "SELECT * FROM users WHERE user_id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		Users user = null;
		try (Connection connection = JDBCUitl.getConnection()){
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user_id);	
			rs = statement.executeQuery();
					
			if(rs.next()) {
				user = new Users();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_passwordHash(rs.getString("user_passwordHash"));
				user.setUser_phoneNumber(rs.getString("user_phoneNumber"));
				user.setUser_city(rs.getString("user_city"));
				user.setUser_identity(rs.getInt("user_identity"));
				user.setUser_pictureURL(rs.getString("user_pictureURL"));
				user.setUser_balance(rs.getInt("user_balance"));
				user.setFreelancer_location_prefer(rs.getString("freelancer_location_prefer"));
				user.setFreelancer_exprience(rs.getString("freelancer_exprience"));
				user.setFreelancer_identity(rs.getString("freelancer_identity"));
				user.setFreelancer_profile_status(rs.getInt("freelancer_profile_status"));
				user.setFreelancer_disc(rs.getString("freelancer_disc"));
				user.setUser_register_time(rs.getString("user_register_time"));
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return user;		
	};
	
	public List<Users> searchUserInfoByemail(String email){
		String sql = "SELECT * FROM users WHERE user_email LIKE ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Users> users = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection()){
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + email + "%");	
			rs = statement.executeQuery();
			
			if (rs.next() == false) {
				System.out.println("找不到");
			}
			
			Users user = null;
			while(rs.next()) {
				user = new Users();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_passwordHash(rs.getString("user_passwordHash"));
				user.setUser_passwordHash(rs.getString("user_phoneNumber"));
				user.setUser_city(rs.getString("user_city"));
				user.setUser_identity(rs.getInt("user_identity"));
				user.setUser_pictureURL(rs.getString("user_pictureURL"));
				user.setUser_balance(rs.getInt("user_balance"));
				user.setFreelancer_location_prefer(rs.getString("freelancer_location_prefer"));
				user.setFreelancer_exprience(rs.getString("freelancer_exprience"));
				user.setFreelancer_identity(rs.getString("freelancer_identity"));
				user.setFreelancer_profile_status(rs.getInt("freelancer_profile_status"));
				user.setFreelancer_disc(rs.getString("freelancer_disc"));
				users.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;		
	};
	
	public static boolean validate(String user_email,String user_passwordHash){  
		boolean status=false;  
		String sql = "SELECT * FROM users WHERE user_email = ? AND user_passwordHash = ? AND user_identity = ?";
		ResultSet rs = null;
		try(Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){  
			
			statement.setString(1, user_email);	
			statement.setString(2, user_passwordHash);	
			statement.setInt(3, 3);	 
		    rs = statement.executeQuery();  
		    status=rs.next();  
		          
		}catch(Exception e){
			System.out.println(e);
		}  
			return status;  
		}
	
	
	public static String getUserPictureByEmail(String user_email) {
	    String sql = "SELECT user_pictureURL FROM users WHERE user_email LIKE ?";
	    try (Connection connection = JDBCUitl.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, "%" + user_email + "%");  
	        try (ResultSet rs = statement.executeQuery()) {
	            // 確保有結果行
	            if (rs.next()) {                
	                return rs.getString("user_pictureURL");
	            } else {	                
	                return null;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
