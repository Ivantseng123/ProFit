//package com.ProFit.dao.servicesCRUD;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.ProFit.bean.servicesBean.ServiceBean;
//
//public class ServiceDAO {
//
//	private Connection getConnection() {
//		try {
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/ProFitDB");
//			return ds.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/*
//	 * insertService：新增服務
//		updateService：更新現有服務
//		deleteService：刪除服務
//		findAllServices：尋找所有服務
//		findServiceById：按 ID 尋找服務
//		searchServices：搜尋服務（可按標題、內容、專業ID和使用者ID搜尋）
//		getAllUsers：取得所有用戶
//		getMajorsByUserId：取得特定使用者的所有專業
//		getUserNameById:根據id取得username
//		getMajorNameById:根據id取得majorname
//	 */
//
//	public boolean insertService(ServiceBean service) throws SQLException {
//        String sql = "INSERT INTO [profitDB].[dbo].[service] (user_id, major_id, service_title, service_content, service_price, service_unit_name, service_duration, service_createdate, service_updatedate, service_sample1, service_sample2, service_sample3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, service.getUserId());
//            ps.setInt(2, service.getMajorId());
//            ps.setString(3, service.getServiceTitle());
//            ps.setString(4, service.getServiceContent());
//            ps.setDouble(5, service.getServicePrice());
//            ps.setString(6, service.getServiceUnitName());
//            ps.setDouble(7, service.getServiceDuration());
//            LocalDateTime now = LocalDateTime.now();
//            ps.setTimestamp(8, Timestamp.valueOf(now));
//            ps.setTimestamp(9, Timestamp.valueOf(now));
//            ps.setBytes(10, service.getServiceSample1());
//            ps.setBytes(11, service.getServiceSample2());
//            ps.setBytes(12, service.getServiceSample3());
//            return ps.executeUpdate() > 0;
//        }
//    }
//
//    public boolean updateService(ServiceBean service) throws SQLException {
//        String sql = "UPDATE [profitDB].[dbo].[service] SET user_id = ?, major_id = ?, service_title = ?, service_content = ?, service_price = ?, service_unit_name = ?, service_duration = ?, service_updatedate = ?, service_sample1 = ?, service_sample2 = ?, service_sample3 = ? WHERE service_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, service.getUserId());
//            ps.setInt(2, service.getMajorId());
//            ps.setString(3, service.getServiceTitle());
//            ps.setString(4, service.getServiceContent());
//            ps.setDouble(5, service.getServicePrice());
//            ps.setString(6, service.getServiceUnitName());
//            ps.setDouble(7, service.getServiceDuration());
//            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
//            ps.setBytes(9, service.getServiceSample1());
//            ps.setBytes(10, service.getServiceSample2());
//            ps.setBytes(11, service.getServiceSample3());
//            ps.setInt(12, service.getServiceId());
//            return ps.executeUpdate() > 0;
//        }
//    }
//
//    public boolean deleteService(int id) throws SQLException {
//        String sql = "DELETE FROM [profitDB].[dbo].[service] WHERE service_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            return ps.executeUpdate() > 0;
//        }
//    }
//
//    public List<ServiceBean> findAllServices() throws SQLException {
//        List<ServiceBean> services = new ArrayList<>();
//        String sql = "SELECT * FROM [profitDB].[dbo].[service]";
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                services.add(extractServiceFromResultSet(rs));
//            }
//        }
//        return services;
//    }
//
//    public ServiceBean findServiceById(int id) throws SQLException {
//        String sql = "SELECT * FROM [profitDB].[dbo].[service] WHERE service_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return extractServiceFromResultSet(rs);
//                }
//            }
//        }
//        return null;
//    }
//
//    public List<ServiceBean> searchServices(String titleKeyword, String contentKeyword, Integer majorId, Integer userId) throws SQLException {
//        List<ServiceBean> services = new ArrayList<>();
//        StringBuilder sql = new StringBuilder("SELECT * FROM [profitDB].[dbo].[service] WHERE 1=1");
//        List<Object> params = new ArrayList<>();
//
//        if (titleKeyword != null && !titleKeyword.isEmpty()) {
//            sql.append(" AND service_title LIKE ?");
//            params.add("%" + titleKeyword + "%");
//        }
//        if (contentKeyword != null && !contentKeyword.isEmpty()) {
//            sql.append(" AND service_content LIKE ?");
//            params.add("%" + contentKeyword + "%");
//        }
//        if (majorId != null) {
//            sql.append(" AND major_id = ?");
//            params.add(majorId);
//        }
//        if (userId != null) {
//            sql.append(" AND user_id = ?");
//            params.add(userId);
//        }
//
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//            for (int i = 0; i < params.size(); i++) {
//                ps.setObject(i + 1, params.get(i));
//            }
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    services.add(extractServiceFromResultSet(rs));
//                }
//            }
//        }
//        return services;
//    }
//
//    public Map<Integer, String> getAllUsers() throws SQLException {
//        Map<Integer, String> users = new HashMap<>();
//        String sql = "SELECT user_id, user_name FROM [profitDB].[dbo].[users]";
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                users.put(rs.getInt("user_id"), rs.getString("user_name"));
//            }
//        }
//        return users;
//    }
//
//    public Map<Integer, String> getAllMajors() throws SQLException {
//        Map<Integer, String> majors = new HashMap<>();
//        String sql = "SELECT major_id, major_name FROM [profitDB].[dbo].[major]";
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                majors.put(rs.getInt("major_id"), rs.getString("major_name"));
//            }
//        }
//        return majors;
//    }
//
//    public Map<Integer, String> getMajorsByUserId(int userId) throws SQLException {
//        Map<Integer, String> majors = new HashMap<>();
//        String sql = "SELECT m.major_id, m.major_name FROM [profitDB].[dbo].[major] m " +
//                     "JOIN [profitDB].[dbo].[user_major] um ON m.major_id = um.major_id " +
//                     "WHERE um.user_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    majors.put(rs.getInt("major_id"), rs.getString("major_name"));
//                }
//            }
//        }
//        return majors;
//    }
//
//    public String getUserNameById(int userId) throws SQLException {
//        String sql = "SELECT user_name FROM [profitDB].[dbo].[users] WHERE user_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("user_name");
//                }
//            }
//        }
//        return "Unknown User";
//    }
//
//    public String getMajorNameById(int majorId) throws SQLException {
//        String sql = "SELECT major_name FROM [profitDB].[dbo].[major] WHERE major_id = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, majorId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("major_name");
//                }
//            }
//        }
//        return "Unknown Major";
//    }
//
//    private ServiceBean extractServiceFromResultSet(ResultSet rs) throws SQLException {
//        ServiceBean service = new ServiceBean();
//        service.setServiceId(rs.getInt("service_id"));
//        service.setUserId(rs.getInt("user_id"));
//        service.setMajorId(rs.getInt("major_id"));
//        service.setServiceTitle(rs.getString("service_title"));
//        service.setServiceContent(rs.getString("service_content"));
//        service.setServicePrice(rs.getDouble("service_price"));
//        service.setServiceUnitName(rs.getString("service_unit_name"));
//        service.setServiceDuration(rs.getDouble("service_duration"));
//        service.setServiceCreateDate(rs.getTimestamp("service_createdate").toLocalDateTime());
//        service.setServiceUpdateDate(rs.getTimestamp("service_updatedate").toLocalDateTime());
//        service.setServiceSample1(rs.getBytes("service_sample1"));
//        service.setServiceSample2(rs.getBytes("service_sample2"));
//        service.setServiceSample3(rs.getBytes("service_sample3"));
//        return service;
//    }
//}