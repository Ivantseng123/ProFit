package com.ProFit.dao.eventsCRUD;

import com.ProFit.bean.EventsBean;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EventsDAO {
    
    private Connection getConnection() {
        Connection conn= null;
        try {
        	Context context;
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/ProFitDB");
			conn = ds.getConnection();
        }  catch (SQLException e) {
            System.err.println("Failed to connect to the database");
            e.printStackTrace();
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn;
    }

    public List<EventsBean> selectAllEvents() {
        List<EventsBean> events = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public EventsBean selectEventById(String eventId) {
        String sql = "SELECT * FROM events WHERE event_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eventId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEvent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertEvent(EventsBean event) {
        String sql = "INSERT INTO events (event_name, is_event_active, event_start_date, event_end_date, event_description, event_amount, event_location, event_participant_maximum, event_note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getEventName());
            pstmt.setBoolean(2, event.isEventActive());
            pstmt.setObject(3, event.getEventStartDate());
            pstmt.setObject(4, event.getEventEndDate());
            pstmt.setString(5, event.getEventDescription());
            pstmt.setInt(6, event.getEventAmount());
            pstmt.setString(7, event.getEventLocation());
            pstmt.setInt(8, event.getEventParticipantMaximum());
            pstmt.setString(9, event.getEventNote());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(EventsBean event) {
        String sql = "UPDATE events SET event_name = ?, is_event_active = ?, event_start_date = ?, event_end_date = ?, event_description = ?, event_amount = ?, event_location = ?, event_participant_maximum = ?, event_note = ? WHERE event_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getEventName());
            pstmt.setBoolean(2, event.isEventActive());
            pstmt.setObject(3, event.getEventStartDate());
            pstmt.setObject(4, event.getEventEndDate());
            pstmt.setString(5, event.getEventDescription());
            pstmt.setInt(6, event.getEventAmount());
            pstmt.setString(7, event.getEventLocation());
            pstmt.setInt(8, event.getEventParticipantMaximum());
            pstmt.setString(9, event.getEventNote());
            pstmt.setString(10, event.getEventId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(String eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eventId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EventsBean mapResultSetToEvent(ResultSet rs) throws SQLException {
        return new EventsBean(
                rs.getString("event_id"),
                rs.getString("event_name"),
                rs.getBoolean("is_event_active"),
                rs.getObject("event_start_date", LocalDateTime.class),
                rs.getObject("event_end_date", LocalDateTime.class),
                rs.getString("event_description"),
                rs.getInt("event_amount"),
                rs.getString("event_location"),
                rs.getInt("event_participant_maximum"),
                rs.getString("event_note")
        );
    }
}
