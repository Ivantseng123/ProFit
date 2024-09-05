package com.ProFit.dao.usersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProFit.bean.usersBean.Employer_application;

public class empApplDao {
	public void saveEmpApplInfo(Employer_application emp) {
		String sql = "INSERT INTO employer_application(user_id,company_name,company_address,company_category,company_phoneNumber,company_taxID,company_taxID_docURL,user_national_id,idCard_pictureURL_1,idCard_pictureURL_2,application_check) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){

			statement.setInt(1,emp.getUser_id());
			statement.setString(2, emp.getCompany_name());
			statement.setString(3, emp.getCompany_address());
			statement.setString(4, emp.getCompany_category());
			statement.setString(5,emp.getCompany_phoneNumber());
			statement.setString(6, emp.getCompany_taxID());
			statement.setString(7, emp.getCompany_taxID_docURL());
			statement.setString(8, emp.getUser_national_id());
			statement.setString(9, emp.getIdCard_pictureURL_1());
			statement.setString(10, emp.getIdCard_pictureURL_2());
			statement.setInt(11, 0);

			statement.execute();
			System.out.println("新增完成");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteEmpInfo(int employer_application_id) {
		String sql = "DELETE FROM employer_application WHERE employer_application_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){

			statement.setInt(1,employer_application_id);

			statement.execute();
			System.out.println("刪除成功");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateEmpApplInfo(Employer_application emp) {
		String sql = "UPDATE employer_application SET company_name = ?, company_address = ?, company_category = ?, company_phoneNumber = ?,"
				+ "company_taxID = ?, company_taxID_docURL = ?, user_national_id = ?, idCard_pictureURL_1 = ?, idCard_pictureURL_2 = ? WHERE employer_application_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){


			statement.setString(1, emp.getCompany_name());
			statement.setString(2, emp.getCompany_address());
			statement.setString(3, emp.getCompany_category());
			statement.setString(4, emp.getCompany_phoneNumber());
			statement.setString(5, emp.getCompany_taxID());
			statement.setString(6, emp.getCompany_taxID_docURL());
			statement.setString(7, emp.getUser_national_id());
			statement.setString(8, emp.getIdCard_pictureURL_1());
			statement.setString(9, emp.getIdCard_pictureURL_2());
			statement.setInt(10, emp.getEmployer_application_id());

			statement.executeUpdate();
			System.out.println("更新成功");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateEmpApplcheck_pass(int employer_application_id) {
		String sql = "UPDATE employer_application SET application_check = ? WHERE employer_application_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){


			statement.setInt(1, 1);
			statement.setInt(2, employer_application_id);


			statement.executeUpdate();
			System.out.println("更新成功");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateEmpApplcheck_reject(int employer_application_id) {
		String sql = "UPDATE employer_application SET application_check = ? WHERE employer_application_id = ?";

		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){


			statement.setInt(1, 2);
			statement.setInt(2, employer_application_id);


			statement.executeUpdate();
			System.out.println("更新成功");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Employer_application> getAllEmpApplInfo(){
		String sql = "SELECT e.employer_application_id, e.user_id, u.user_email, e.company_name, e.company_address, e.company_category, e.company_phoneNumber, e.company_taxID, e.company_taxID_docURL, e.user_national_id, e.idCard_pictureURL_1, e.idCard_pictureURL_2, e.application_check FROM employer_application AS e JOIN users AS u ON e.user_id = u.user_id";
		List<Employer_application> emps = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();){

			Employer_application emp = null;
			while(rs.next()) {
				emp = new Employer_application();
				emp.setEmployer_application_id(rs.getInt("employer_application_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_taxID_docURL(rs.getString("company_taxID_docURL"));
				emp.setUser_national_id(rs.getString("company_taxID_docURL"));
				emp.setIdCard_pictureURL_1(rs.getString("idCard_pictureURL_1"));
				emp.setIdCard_pictureURL_2(rs.getString("idCard_pictureURL_2"));
				emp.setApplication_check(rs.getInt("application_check"));

				emps.add(emp);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return emps;
	}

	public List<Employer_application> searchEmpApplInfo(String email){
		String sql = "SELECT e.employer_application_id, e.user_id, u.user_email, e.company_name, e.company_address, e.company_category, e.company_phoneNumber, e.company_taxID, e.company_taxID_docURL, e.user_national_id, e.idCard_pictureURL_1, e.idCard_pictureURL_2, e.application_check FROM employer_application AS e JOIN users AS u ON e.user_id = u.user_id WHERE u.user_email LIKE ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Employer_application> emps = new ArrayList<>();
		try (Connection connection = JDBCUitl.getConnection()){

			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + email + "%");
			rs = statement.executeQuery();

			if (!rs.next()) {
				System.out.println("找不到");
			}

			Employer_application emp = null;
			while(rs.next()) {
				emp = new Employer_application();
				emp.setEmployer_application_id(rs.getInt("employer_profile_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setCompany_name(rs.getString("user_email"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_taxID_docURL(rs.getString("company_taxID_docURL"));
				emp.setUser_national_id(rs.getString("company_taxID_docURL"));
				emp.setIdCard_pictureURL_1(rs.getString("idCard_pictureURL_1"));
				emp.setIdCard_pictureURL_2(rs.getString("idCard_pictureURL_2"));
				emp.setApplication_check(rs.getInt("application_check"));
				emps.add(emp);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return emps;
	}

	public Employer_application getEmpApplInfoByID(int employer_application_id){
		String sql = "SELECT * FROM employer_application WHERE employer_application_id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		Employer_application emp = null;
		try (Connection connection = JDBCUitl.getConnection()){

			statement = connection.prepareStatement(sql);
			statement.setInt(1, employer_application_id);
			rs = statement.executeQuery();

			if(rs.next()) {
				emp = new Employer_application();
				emp.setEmployer_application_id(rs.getInt("employer_application_id"));
				emp.setUser_id(rs.getInt("user_id"));
				emp.setCompany_name(rs.getString("company_name"));
				emp.setCompany_address(rs.getString("company_address"));
				emp.setCompany_category(rs.getString("company_category"));
				emp.setCompany_phoneNumber(rs.getString("company_phoneNumber"));
				emp.setCompany_taxID(rs.getString("company_taxID"));
				emp.setCompany_taxID_docURL(rs.getString("company_taxID_docURL"));
				emp.setUser_national_id(rs.getString("user_national_id"));
				emp.setIdCard_pictureURL_1(rs.getString("idCard_pictureURL_1"));
				emp.setIdCard_pictureURL_2(rs.getString("idCard_pictureURL_2"));
				emp.setApplication_check(rs.getInt("application_check"));
			}

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return emp;
	}
}
