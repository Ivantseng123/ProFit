package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.JobOrderBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobOrderDAO {
    private DataSource dataSource;

    public JobOrderDAO() {
        try {
            Context context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/ProFitDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public List<JobOrderBean> getAllOrders() {
        List<JobOrderBean> orders = new ArrayList<>();
        String sql = "SELECT * FROM job_orders ORDER BY job_order_date DESC"; // 按訂單日期降序排列
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                orders.add(new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean insertOrder(JobOrderBean order) {
        String sql = "INSERT INTO job_orders (job_orders_id, job_application_id, job_order_date, job_order_status, job_notes, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String jobOrdersId = UUID.randomUUID().toString();
            stmt.setString(1, jobOrdersId);

            stmt.setInt(2, order.getJobApplicationId());
            stmt.setTimestamp(3, order.getJobOrderDate());
            stmt.setString(4, order.getJobOrderStatus());
            stmt.setString(5, order.getJobNotes());
            stmt.setInt(6, order.getTotalAmount());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrder(JobOrderBean order) {
        String sql = "UPDATE job_orders SET job_application_id = ?, job_order_date = ?, job_order_status = ?, job_notes = ?, total_amount = ? WHERE job_orders_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getJobApplicationId());
            stmt.setTimestamp(2, order.getJobOrderDate());
            stmt.setString(3, order.getJobOrderStatus());
            stmt.setString(4, order.getJobNotes());
            stmt.setInt(5, order.getTotalAmount());
            stmt.setString(6, order.getJobOrdersId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrder(String jobOrdersId) {
        String sql = "DELETE FROM job_orders WHERE job_orders_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jobOrdersId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JobOrderBean getOrderById(String jobOrdersId) {
        String sql = "SELECT * FROM job_orders WHERE job_orders_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jobOrdersId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<JobOrderBean> searchOrdersByApplicationId(int jobApplicationId) {
        List<JobOrderBean> orders = new ArrayList<>();
        String sql = "SELECT * FROM job_orders WHERE job_application_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jobApplicationId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<JobOrderBean> searchOrdersByDate(Timestamp startDate, Timestamp endDate) {
        List<JobOrderBean> orders = new ArrayList<>();
        String sql = "SELECT * FROM job_orders WHERE job_order_date BETWEEN ? AND ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, startDate);
            stmt.setTimestamp(2, endDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<JobOrderBean> searchOrdersByStatus(String jobOrderStatus) {
        List<JobOrderBean> orders = new ArrayList<>();
        String sql = "SELECT * FROM job_orders WHERE job_order_status = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jobOrderStatus);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<JobOrderBean> searchOrdersByCriteria(Integer jobApplicationId, Timestamp startDate, Timestamp endDate, String jobOrderStatus) {
        List<JobOrderBean> orders = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM job_orders WHERE 1=1");

        if (jobApplicationId != null) {
            sql.append(" AND job_application_id = ?");
        }
        if (startDate != null) {
            sql.append(" AND job_order_date >= ?");
        }
        if (endDate != null) {
            sql.append(" AND job_order_date <= ?");
        }
        if (jobOrderStatus != null && !jobOrderStatus.isEmpty()) {
            sql.append(" AND job_order_status = ?");
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (jobApplicationId != null) {
                stmt.setInt(index++, jobApplicationId);
            }
            if (startDate != null) {
                stmt.setTimestamp(index++, startDate);
            }
            if (endDate != null) {
                stmt.setTimestamp(index++, endDate);
            }
            if (jobOrderStatus != null && !jobOrderStatus.isEmpty()) {
                stmt.setString(index++, jobOrderStatus);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new JobOrderBean(
                        rs.getString("job_orders_id"),
                        rs.getInt("job_application_id"),
                        rs.getTimestamp("job_order_date"),
                        rs.getString("job_order_status"),
                        rs.getString("job_notes"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
