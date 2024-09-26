package com.ProFit.dao.eventsCRUD;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ProFit.bean.eventsBean.EventsBean;

public class EventsDAO {

    
    private SessionFactory sessionFactory;

    public EventsDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<EventsBean> selectAllEvents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from EventsBean", EventsBean.class).list();
        }
    }

    public EventsBean selectEventById(String eventId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EventsBean.class, eventId);
        }
    }

    public EventsBean insertEvent(EventsBean event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        } 
        return event;
    }

    public EventsBean updateEvent(EventsBean event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
        }
        return event;
    }

    public void deleteEvent(String eventId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            EventsBean event = session.find(EventsBean.class, eventId);
            session.remove(event);
            transaction.commit();
        }
    }

    // private Connection getConnection() {
    //     Connection conn= null;
    //     try {
    //     	Context context;
	// 		context = new InitialContext();
	// 		DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/ProFitDB");
	// 		conn = ds.getConnection();
    //     }  catch (SQLException e) {
    //         System.err.println("Failed to connect to the database");
    //         e.printStackTrace();
    //     } catch (NamingException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
    //     return conn;
    // }

    // public List<EventsBean> selectAllEvents() {
    //     List<EventsBean> events = new ArrayList<>();
    //     String sql = "SELECT * FROM events";
    //     try (Connection conn = getConnection();
    //          Statement stmt = conn.createStatement();
    //          ResultSet rs = stmt.executeQuery(sql)) {
    //         while (rs.next()) {
    //             events.add(mapResultSetToEvent(rs));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return events;
    // }

    // public EventsBean selectEventById(String eventId) {
    //     String sql = "SELECT * FROM events WHERE event_id = ?";
    //     try (Connection conn = getConnection();
    //          PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //         pstmt.setString(1, eventId);
    //         try (ResultSet rs = pstmt.executeQuery()) {
    //             if (rs.next()) {
    //                 return mapResultSetToEvent(rs);
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    // public void insertEvent(EventsBean event) {
    //     String sql = "INSERT INTO events (event_id, event_name, is_event_active, event_major, event_start_date, event_end_date, event_part_start_date, event_part_end_date, event_amount, event_location, event_participant_maximum, event_description, event_note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //     try (Connection conn = getConnection();
    //          PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //         pstmt.setString(1, event.getEventId());
    //         pstmt.setString(2, event.getEventName());
    //         pstmt.setInt(3, event.getIsEventActive());
    //         pstmt.setInt(4, event.getEventMajor());
    //         pstmt.setObject(5, event.getEventStartDate());
    //         pstmt.setObject(6, event.getEventEndDate());
    //         pstmt.setObject(7, event.getEventPartStartDate());
    //         pstmt.setObject(8, event.getEventPartEndDate());
    //         pstmt.setInt(9, event.getEventAmount());
    //         pstmt.setString(10, event.getEventLocation());
    //         pstmt.setInt(11, event.getEventParticipantMaximum());
    //         pstmt.setString(12, event.getEventDescription());
    //         pstmt.setString(13, event.getEventNote());
    //         pstmt.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void updateEvent(EventsBean event) {
    //     String sql = "UPDATE events SET event_name = ?, is_event_active = ?, event_major = ?, event_start_date = ?, event_end_date = ?, event_part_start_date = ?, event_part_end_date = ?, event_amount = ?, event_location = ?, event_participant_maximum = ?, event_description = ?, event_note = ? WHERE event_id = ?";
    //     try (Connection conn = getConnection();
    //          PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //         pstmt.setString(1, event.getEventName());
    //         pstmt.setInt(2, event.getIsEventActive());
    //         pstmt.setInt(3, event.getEventMajor());
    //         pstmt.setObject(4, event.getEventStartDate());
    //         pstmt.setObject(5, event.getEventEndDate());
    //         pstmt.setObject(6, event.getEventPartStartDate());
    //         pstmt.setObject(7, event.getEventPartEndDate());
    //         pstmt.setInt(8, event.getEventAmount());
    //         pstmt.setString(9, event.getEventLocation());
    //         pstmt.setInt(10, event.getEventParticipantMaximum());
    //         pstmt.setString(11, event.getEventDescription());
    //         pstmt.setString(12, event.getEventNote());
    //         pstmt.setString(13, event.getEventId());
    //         pstmt.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void deleteEvent(String eventId) {
    //     String sql = "DELETE FROM events WHERE event_id = ?";
    //     try (Connection conn = getConnection();
    //          PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //         pstmt.setString(1, eventId);
    //         pstmt.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // private EventsBean mapResultSetToEvent(ResultSet rs) throws SQLException {
    //     return new EventsBean(
    //             rs.getString("event_id"),
    //             rs.getString("event_name"),
    //             rs.getInt("is_event_active"),
    //             rs.getInt("event_major"),
    //             rs.getObject("event_start_date", LocalDateTime.class),
    //             rs.getObject("event_end_date", LocalDateTime.class),
    //             rs.getObject("event_part_start_date", LocalDateTime.class),
    //             rs.getObject("event_part_end_date", LocalDateTime.class),
    //             rs.getInt("event_amount"),
    //             rs.getString("event_location"),
    //             rs.getInt("event_participant_maximum"),
    //             rs.getString("event_description"),
    //             rs.getString("event_note")
    //     );
    // }
}

