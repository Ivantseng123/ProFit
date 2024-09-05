package com.ProFit.dao.jobsCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ProFit.util.CountProperty;

//抽象類別就是為了給別人繼承存在，他實作（implements）這個介面的5個方法
public abstract class AbstractGenericDAO<T, ID> implements GenericDAO<T, ID> {
//    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ProFitDB;encrypt=false;";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "Pp123456";

    protected abstract String getTableName(); //因尚未確定表名稱，故使用抽象方法讓其子類返回表名稱

    //JDBC從資料庫撈回來的東西依照欄位塞回JAVA BEAN，throws 例外（SQLException）不處理
    protected abstract T mapRowToObject(ResultSet rs) throws SQLException;

    //(PreparedStatement stmt, T entity) 這在指定第幾個“?”，需要插入什麼值
    protected abstract void setInsertStatementParameters(PreparedStatement stmt, T entity) throws SQLException;

    //(PreparedStatement stmt, Map<String, Object> updates)  這在指定第幾個“?”，需要插入什麼值，
    // 用Map<String, Object> 取代T entity是為了可以指定欄位修改，
    // Map<String, Object>中，String是要修改的欄位名，Object是要修改的值
    protected abstract void setUpdateStatementParameters(PreparedStatement stmt, Map<String, Object> updates) throws SQLException;

    protected Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
    	Connection conn =null;
    	try {
    		Context context;
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/ProFitDB");
			conn=ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return conn;
    }//JDBC建立連線


    //覆寫GenericDAO的方法
    @Override
    public int save(T entity) {
        String sql = "INSERT INTO " + getTableName() + " (" + getInsertColumns() + ") VALUES " +
                "(" + getInsertPlaceholders(entity) + ")";
        System.out.println("sql===" + sql);
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setInsertStatementParameters(stmt, entity);//這在指定第幾個“?”，需要插入什麼值
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);//獲取 ResultSet 中的第一列，這就是你的 ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;//-1代表返回失敗，-1視為一種錯誤指示符，表示無法成功地從數據庫中獲取生成的 ID。跟上面的1沒有任何關係！！別誤會
    }
    @Override
    public T findById(ID id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE jobs_id = ?";//此處id可改成其他欄位，就可實現其他欄位的查詢
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                entities.add(mapRowToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
    @Override
    public void update(ID id, Map<String, Object> updates) {
        StringBuilder sql = new StringBuilder("UPDATE " + getTableName() + " SET ");
        updates.forEach((key, value) -> sql.append(key).append(" = ?, ")); //使用lambda
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE jobs_id = ?");

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            setUpdateStatementParameters(stmt, updates);
            stmt.setObject(updates.size() + 1, id);  // Set the ID at the last parameter
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(ID id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE jobs_id  = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getInsertColumns();
    public String getInsertPlaceholders(T entity) {
        return CountProperty.getPropertyCount(entity.getClass());//反射,getClass是取得Class類

    }
}
