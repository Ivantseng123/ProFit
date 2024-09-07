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

import com.ProFit.bean.coursesBean.CourseBean;

public class CourseDao {

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context context;
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/ProFitDB");
			conn = ds.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 新增課程
	public boolean insertCourse(CourseBean course) {
	    boolean status = true;

	    // 先查詢當前最大值的數值部分
	    final String GetMaxCourseIdSQL = "SELECT MAX(CAST(SUBSTRING(course_id, 2, LEN(course_id) - 1) AS INT)) FROM courses";

	    // 插入新課程的SQL
	    final String InsertCourseSQL = "INSERT INTO courses(course_id, course_name, "
	            + "course_create_user_id, course_category, course_information, course_description, "
	            + "course_enrollment_date, course_start_date, course_end_date, course_price, course_status) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = getConnection();

	        // 查詢最大值
	        stmt = conn.prepareStatement(GetMaxCourseIdSQL);
	        rs = stmt.executeQuery();

	        int newCourseIdNumber = 100; // 默認起始值為100
	        if (rs.next()) {
	            int maxCourseIdNumber = rs.getInt(1);
	            if (rs.wasNull()) {
	                maxCourseIdNumber = 99;
	            }
	            newCourseIdNumber = maxCourseIdNumber + 1;
	        }

	        // 格式化新的course_id
	        String newCourseId = String.format("C%04d", newCourseIdNumber); // 生成格式如 C0101 的 ID
	        System.out.println(newCourseId);

	        // 關閉先前的PreparedStatement
	        stmt.close();

	        // 使用新的course_id來插入新資料
	        stmt = conn.prepareStatement(InsertCourseSQL);
	        stmt.setString(1, newCourseId);
	        stmt.setString(2, course.getCourseName());
	        stmt.setInt(3, Integer.parseInt(course.getCourseCreateUserId()));
	        stmt.setInt(4, Integer.parseInt(course.getCourseCategory()));
	        stmt.setString(5, course.getCourseInformation());
	        stmt.setString(6, course.getCourseDescription());
	        stmt.setString(7, course.getCourseEnrollmentDate());
	        stmt.setString(8, course.getCourseStartDate());
	        stmt.setString(9, course.getCourseEndDate());
	        stmt.setInt(10, Integer.parseInt(course.getCoursePrice()));
	        stmt.setString(11, course.getCourseStatus());

	        stmt.execute();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        status = false;
	    } finally {
	        try {
	            if (rs != null) {
					rs.close();
				}
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

	// 刪除課程By course_id
	public boolean deleteCourseByID(String courseId) {
		boolean status = true;

		final String DeleteCourseSQL="DELETE FROM courses WHERE course_id=?";
		Connection conn=null;
		PreparedStatement stmt =null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(DeleteCourseSQL);
			stmt.setString(1, courseId);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			status=false;
		}finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return status;
	}

	// 修改課程
	public boolean updateCourseById(CourseBean newCourse,CourseBean oldCourse) {
		boolean status = true;

		final String UpdateEmpSQL="UPDATE courses SET course_name=?,course_create_user_id=?,course_category=?,"
				+ "course_information=?,course_description=?,course_enrollment_date=?,course_start_date=?,"
				+ "course_end_date=?,course_price=?,course_status=? WHERE course_id=?" ;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UpdateEmpSQL);

	        stmt.setString(1, newCourse.getCourseName().isEmpty() ? oldCourse.getCourseName() : newCourse.getCourseName());
	        stmt.setString(2, newCourse.getCourseCreateUserId().isEmpty() ? oldCourse.getCourseCreateUserId() : newCourse.getCourseCreateUserId());
	        stmt.setString(3, newCourse.getCourseCategory().isEmpty() ? oldCourse.getCourseCategory() : newCourse.getCourseCategory());
	        stmt.setString(4, newCourse.getCourseInformation().isEmpty() ? oldCourse.getCourseInformation() : newCourse.getCourseInformation());
	        stmt.setString(5, newCourse.getCourseDescription().isEmpty() ? oldCourse.getCourseDescription() : newCourse.getCourseDescription());
	        stmt.setString(6, newCourse.getCourseEnrollmentDate().isEmpty() ? oldCourse.getCourseEnrollmentDate() : newCourse.getCourseEnrollmentDate());
	        stmt.setString(7, newCourse.getCourseStartDate().isEmpty() ? oldCourse.getCourseStartDate() : newCourse.getCourseStartDate());
	        stmt.setString(8, newCourse.getCourseEndDate().isEmpty() ? oldCourse.getCourseEndDate() : newCourse.getCourseEndDate());
	        stmt.setString(9, newCourse.getCoursePrice().isEmpty() ? oldCourse.getCoursePrice() : newCourse.getCoursePrice());
	        stmt.setString(10, newCourse.getCourseStatus().isEmpty() ? oldCourse.getCourseStatus() : newCourse.getCourseStatus());
	        stmt.setString(11, oldCourse.getCourseId());
			stmt.executeUpdate();
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

	// 查詢單筆By couseId
	public CourseBean findSingleCourseById(String courseId) {
		final String GetCourseSQL="SELECT c.*, u.user_name FROM courses c INNER JOIN users u ON c.course_create_user_id = u.user_id WHERE course_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		CourseBean course =null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(GetCourseSQL);
			stmt.setString(1, courseId);
			ResultSet rs = stmt.executeQuery();
			course = new CourseBean();
			if (rs.next()) {
	            course.setCourseId(rs.getString("course_id"));
	            course.setCourseName(rs.getString("course_name"));
	            course.setCourseCreateUserId(rs.getString("course_create_user_id"));
	            course.setCourseCategory(rs.getString("course_category"));
	            course.setCourseInformation(rs.getString("course_information"));
	            course.setCourseDescription(rs.getString("course_description"));
	            course.setCourseEnrollmentDate(rs.getDate("course_enrollment_date").toString());
	            course.setCourseStartDate(rs.getTimestamp("course_start_date").toString());
	            course.setCourseEndDate(rs.getTimestamp("course_end_date").toString());
	            course.setCoursePrice(rs.getString("course_price"));
	            course.setCreateUserName(rs.getString("user_name"));
	            course.setCourseStatus(rs.getString("course_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return course;
	}

	// 查詢全部(未測試)
	public List<CourseBean> searchCourses(){

		final String GetAllCoursesSQL="SELECT c.*, u.user_name FROM courses c INNER JOIN users u ON c.course_create_user_id = u.user_id";
		Connection conn = null;
		PreparedStatement stmt = null;
		List<CourseBean> courses=null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(GetAllCoursesSQL);
			ResultSet rs = stmt.executeQuery();
			courses =new ArrayList<>();
			CourseBean course = null;
			while (rs.next()) {
				course = new CourseBean();
				course.setCourseId(rs.getString("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseCreateUserId(rs.getString("course_create_user_id"));
				course.setCourseCategory(rs.getString("course_category"));
				course.setCourseInformation(rs.getString("course_information"));
				course.setCourseDescription(rs.getString("course_description"));
				course.setCourseEnrollmentDate(rs.getDate("course_enrollment_date").toString());
				course.setCourseStartDate(rs.getDate("course_start_date").toString());
				course.setCourseEndDate(rs.getDate("course_end_date").toString());
				course.setCoursePrice(rs.getString("course_price"));
				course.setCreateUserName(rs.getString("user_name"));
				course.setCourseStatus(rs.getString("course_status"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	// 查詢全部By 多條件查詢
	public List<CourseBean> searchCourses(String courseName, String userName, String status, String userId, String category) {
	    StringBuilder sql = new StringBuilder("SELECT c.*, u.user_name FROM courses c INNER JOIN users u ON c.course_create_user_id = u.user_id WHERE 1=1");
	    List<Object> params = new ArrayList<>();

	    if (courseName != null && !courseName.trim().isEmpty()) {
	        sql.append(" AND c.course_name LIKE ?");
	        params.add("%" + courseName + "%");
	    }
	    if (userName != null && !userName.trim().isEmpty()) {
	        sql.append(" AND u.user_name LIKE ?");
	        params.add("%" + userName + "%");
	    }
	    if (status != null && !status.trim().isEmpty()) {
	        sql.append(" AND c.course_status = ?");
	        params.add(status);
	    }
	    if (userId != null && !userId.trim().isEmpty()) {
	        sql.append(" AND c.course_create_user_id = ?");
	        params.add(Integer.parseInt(userId));
	    }
	    if (category != null && !category.trim().isEmpty()) {
	        sql.append(" AND c.course_category = ?");
	        params.add(Integer.parseInt(category));
	    }

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    List<CourseBean> courses = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        conn = getConnection(); // 獲取資料庫連接
	        stmt = conn.prepareStatement(sql.toString()); // 動態生成SQL語句
	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i)); // 設置查詢參數
	        }

	        rs = stmt.executeQuery(); // 執行查詢

	        while (rs.next()) {
	            CourseBean course = new CourseBean();
	            course.setCourseId(rs.getString("course_id"));
	            course.setCourseName(rs.getString("course_name"));
	            course.setCourseCreateUserId(rs.getString("course_create_user_id"));
	            course.setCourseCategory(rs.getString("course_category"));
	            course.setCourseInformation(rs.getString("course_information"));
	            course.setCourseDescription(rs.getString("course_description"));
	            course.setCourseEnrollmentDate(rs.getDate("course_enrollment_date").toString());
	            course.setCourseStartDate(rs.getDate("course_start_date").toString());
	            course.setCourseEndDate(rs.getDate("course_end_date").toString());
	            course.setCoursePrice(rs.getString("course_price"));
	            course.setCreateUserName(rs.getString("user_name"));
	            course.setCourseStatus(rs.getString("course_status"));
	            courses.add(course);
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
	            if (rs !=null) {
					rs.close();
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return courses;
	}

}
