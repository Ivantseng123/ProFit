package com.ProFit.dao.transactionCRUD;

import com.ProFit.bean.UserTransactionBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionDAO {
    private DataSource dataSource;

    public UserTransactionDAO() {
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

    public List<UserTransactionBean> getAllTransactions() {
        List<UserTransactionBean> transactions = new ArrayList<>();
        String sql = "SELECT ut.transaction_id, ut.user_id, ut.transaction_type, ut.amount, ut.transaction_status, ut.created_at, u.user_name " +
                     "FROM user_transactions ut " +
                     "JOIN users u ON ut.user_id = u.user_id " +
                     "ORDER BY ut.transaction_id DESC";  // 按交易ID降序排序
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                transactions.add(new UserTransactionBean(
                        rs.getString("transaction_id"),
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("transaction_type"),
                        rs.getString("amount"),
                        rs.getString("transaction_status"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<UserTransactionBean> getTransactionsByFilters(String userId, String userName, String transactionType, String transactionStatus, Timestamp startDate, Timestamp endDate) {
        List<UserTransactionBean> transactions = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT ut.transaction_id, ut.user_id, ut.transaction_type, ut.amount, ut.transaction_status, ut.created_at, u.user_name ");
        sql.append("FROM user_transactions ut ");
        sql.append("JOIN users u ON ut.user_id = u.user_id ");
        sql.append("WHERE 1=1 ");

        if (userId != null && !userId.isEmpty()) {
            sql.append("AND ut.user_id = ? ");
        }
        if (userName != null && !userName.isEmpty()) {
            sql.append("AND u.user_name LIKE ? ");
        }
        if (transactionType != null && !transactionType.isEmpty()) {
            sql.append("AND ut.transaction_type = ? ");
        }
        if (transactionStatus != null && !transactionStatus.isEmpty()) {
            sql.append("AND ut.transaction_status = ? ");
        }
        if (startDate != null) {
            sql.append("AND ut.created_at >= ? ");
        }
        if (endDate != null) {
            sql.append("AND ut.created_at <= ? ");
        }

        sql.append("ORDER BY ut.transaction_id DESC");  // 按交易ID降序排序

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (userId != null && !userId.isEmpty()) {
                stmt.setString(paramIndex++, userId);
            }
            if (userName != null && !userName.isEmpty()) {
                stmt.setString(paramIndex++, "%" + userName + "%");
            }
            if (transactionType != null && !transactionType.isEmpty()) {
                stmt.setString(paramIndex++, transactionType);
            }
            if (transactionStatus != null && !transactionStatus.isEmpty()) {
                stmt.setString(paramIndex++, transactionStatus);
            }
            if (startDate != null) {
                stmt.setTimestamp(paramIndex++, startDate);
            }
            if (endDate != null) {
                stmt.setTimestamp(paramIndex++, endDate);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new UserTransactionBean(
                            rs.getString("transaction_id"),
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("transaction_type"),
                            rs.getString("amount"),
                            rs.getString("transaction_status"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public boolean insertTransaction(UserTransactionBean transaction) {
        String sql = "INSERT INTO user_transactions (user_id, transaction_type, amount, transaction_status, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 不要設置 transaction_id，因為它是自動生成的
            stmt.setString(1, transaction.getUserId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setString(3, transaction.getAmount());
            stmt.setString(4, transaction.getTransactionStatus());
            stmt.setTimestamp(5, transaction.getCreatedAt());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTransaction(UserTransactionBean transaction) {
        String sql = "UPDATE user_transactions SET user_id = ?, transaction_type = ?, amount = ?, transaction_status = ?, created_at = ? WHERE transaction_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getUserId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setString(3, transaction.getAmount());
            stmt.setString(4, transaction.getTransactionStatus());
            stmt.setTimestamp(5, transaction.getCreatedAt());
            stmt.setString(6, transaction.getTransactionId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTransaction(String transactionId) {
        String sql = "DELETE FROM user_transactions WHERE transaction_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transactionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserTransactionBean getTransactionById(String transactionId) {
        String sql = "SELECT ut.transaction_id, ut.user_id, ut.transaction_type, ut.amount, ut.transaction_status, ut.created_at, u.user_name " +
                     "FROM user_transactions ut " +
                     "JOIN users u ON ut.user_id = u.user_id " +
                     "WHERE ut.transaction_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserTransactionBean(
                        rs.getString("transaction_id"),
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("transaction_type"),
                        rs.getString("amount"),
                        rs.getString("transaction_status"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
