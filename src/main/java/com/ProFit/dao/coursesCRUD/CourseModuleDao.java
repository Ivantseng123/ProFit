package com.ProFit.dao.coursesCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ProFit.bean.CourseModuleBean;

public class CourseModuleDao {

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context context;
			context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/ProFitDB");
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

	// 新增章節
	public boolean createModule(CourseModuleBean courseModule) {
		boolean status = true;

		// 插入新章節的SQL
		final String insertCourseModuleSQL = "INSERT INTO course_module(course_id,course_module_name)" + "VALUES (?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(insertCourseModuleSQL);
			stmt.setString(1, courseModule.getCourseId());
			stmt.setString(2, courseModule.getCourseModuleName());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	// 刪除章節
	public boolean deleteMouduleById(String courseModuleId) {
		boolean status = true;

		final String deleteCourseMouduleSQL = "DELETE FROM course_module WHERE course_module_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(deleteCourseMouduleSQL);
			stmt.setString(1, courseModuleId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	// 修改章節
	public boolean updateCourseModuleById(CourseModuleBean newCourseModule , CourseModuleBean oldCourseModule) {
		boolean status = true;




		return status;
	}



	// 查詢全部章節By CourseId
	public List<CourseModuleBean> searchAllCourseModule(String courseId) {
		List<CourseModuleBean> courseModules = new ArrayList<>();

		final String searchAllCourseModuleSQL = "SELECT * FROM course_module WHERE course_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(searchAllCourseModuleSQL);
			stmt.setString(1, courseId);
			rs = stmt.executeQuery();
			CourseModuleBean courseModule = null;
			if (rs.next()) {
				courseModule = new CourseModuleBean();
				courseModule.setCourseModuleId(rs.getString("course_module_id"));
				courseModule.setCourseId("course_id");
				courseModule.setCourseModuleName("course_module_name");
				courseModules.add(courseModule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courseModules;
	}

	// 查詢單筆章節By course_module_id
	public CourseModuleBean searchOneCourseModuleById(String courseModuleId) {
		CourseModuleBean courseModule = new CourseModuleBean();

		final String searchOneCourseModuleSQL = "SELECT * FROM course_module WHERE course_module_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(searchOneCourseModuleSQL);
			stmt.setString(1, courseModuleId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				courseModule.setCourseModuleId(rs.getString("course_module_id"));
				courseModule.setCourseId(rs.getString("course_id"));
				courseModule.setCourseModuleName(rs.getString("course_module_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return courseModule;
	}

}
