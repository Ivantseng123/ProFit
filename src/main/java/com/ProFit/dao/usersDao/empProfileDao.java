package com.ProFit.dao.usersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProFit.bean.usersBean.Employer_profile;

public class empProfileDao {
	public void saveEmployerInfo(Employer_profile emp) {
		String sql = "INSERT INTO employer_profile(user_id,company_name,company_address,company_category,company_phoneNumber,company_taxID) VALUES(?,?,?,?,?,?)";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){

			statement.setInt(1,emp.getUser_id());
			statement.setString(2, emp.getCompany_name());
			statement.setString(3, emp.getCompany_address());
			statement.setString(4, emp.getCompany_category());
			statement.setString(5,emp.getCompany_phoneNumber());
			statement.setString(6, emp.getCompany_taxID());

			statement.execute();
			System.out.println("新增完成");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteEmpInfo(int employer_profile_id) {
		String sql = "DELETE FROM employer_profile WHERE employer_profile_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){

			statement.setInt(1,employer_profile_id);

			statement.execute();
			System.out.println("刪除成功");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateEmpInfo(Employer_profile emp) {
		String sql = "UPDATE employer_profile SET company_name = ?, company_address = ?, company_category = ?, company_phoneNumber = ?,"
				+ "company_taxID = ?, company_numberOfemployee = ?, company_captital = ?, company_description = ?, company_photoURL = ? WHERE employer_profile_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){


			statement.setString(1, emp.getCompany_name());
			statement.setString(2, emp.getCompany_address());
			statement.setString(3, emp.getCompany_category());
			statement.setString(4, emp.getCompany_phoneNumber());
			statement.setString(5, emp.getCompany_taxID());
			statement.setString(6, emp.getCompany_numberOfemployee());
			statement.setString(7, emp.getCompany_captital());
			statement.setString(8, emp.getCompany_description());
			statement.setString(9, emp.getCompany_photoURL());
			statement.setInt(10, emp.getEmployer_profile_id());

			statement.executeUpdate();
			System.out.println("更新成功");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Employer_profile> getAllEmpInfo(){
		String sql = "SELECT e.employer_profile_id, e.user_id, u.user_email, e.company_name, e.company_address, e.company_category, e.company_phoneNumber, e.company_taxID, e.company_numberOfemployee, e.company_captital, e.company_description, e.company_photoURL FROM employer_profile AS e JOIN users AS u ON e.user_id = u.user_id";
		List<Employer_profile> emps = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();){

			Employer_profile emp = null;
			while(rs.next()) {
				emp = new Employer_profile();
				emp.setEmployer_profile_id(rs.getInt("employer_profile_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_numberOfemployee(rs.getString("company_numberOfemployee"));
				emp.setCompany_captital(rs.getString("company_captital"));
				emp.setCompany_description(rs.getString("company_description"));
				emp.setCompany_photoURL(rs.getString("company_photoURL"));

				emps.add(emp);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return emps;
	}

	public List<Employer_profile> searchEmpInfo(String email){
		String sql = "SELECT e.employer_profile_id, e.user_id, u.user_email, e.company_name, e.company_address, e.company_category, e.company_phoneNumber, e.company_taxID, e.company_numberOfemployee, e.company_captital, e.company_description, e.company_photoURL FROM employer_profile AS e JOIN users AS u ON e.user_id = u.user_id WHERE u.user_email LIKE ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Employer_profile> emps = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection()){

			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + email + "%");
			rs = statement.executeQuery();

			if (!rs.next()) {
				System.out.println("找不到");
			}

			Employer_profile emp = null;
			while(rs.next()) {
				emp = new Employer_profile();
				emp.setEmployer_profile_id(rs.getInt("employer_profile_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_numberOfemployee(rs.getString("company_numberOfemployee"));
				emp.setCompany_captital(rs.getString("company_captital"));
				emp.setCompany_description(rs.getString("company_description"));
				emp.setCompany_photoURL(rs.getString("company_photoURL"));
				emps.add(emp);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return emps;
	}

	public Employer_profile getEmpPfInfoByID(int employer_profile_id){
		String sql = "SELECT * FROM employer_profile WHERE employer_profile_id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		Employer_profile emp = null;
		try (Connection connection = JDBCUitl.getConnection()){

			statement = connection.prepareStatement(sql);
			statement.setInt(1, employer_profile_id);
			rs = statement.executeQuery();

			if(rs.next()) {
				emp = new Employer_profile();
				emp.setEmployer_profile_id(rs.getInt("employer_profile_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_numberOfemployee(rs.getString("company_numberOfemployee"));
				emp.setCompany_captital(rs.getString("company_captital"));
				emp.setCompany_description(rs.getString("company_description"));
				emp.setCompany_photoURL(rs.getString("company_photoURL"));
			}

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return emp;
	}
}
