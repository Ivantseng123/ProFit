package com.ProFit.dao.coursesCRUD;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CourseGradeContentDao {
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context context;
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/ProFitDB");
			conn = ds.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	//新增

	//刪除

	//查詢全部

	//查詢單筆

	//修改
}
