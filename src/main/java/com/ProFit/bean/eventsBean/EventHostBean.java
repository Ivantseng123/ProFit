package com.ProFit.bean.eventsBean;

import java.io.Serializable;

import jakarta.persistence.*;

//@Entity
//@Table(name = "event_host")
public class EventHostBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private String eventId;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "event_host_id", referencedColumnName = "user_id")
	private int eventHostId;

	public EventHostBean() {
	}

	public EventHostBean(String eventId, int eventHostId) {
		super();
		this.eventId = eventId;
		this.eventHostId = eventHostId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getEventHostId() {
		return eventHostId;
	}

	public void setEventHostId(int eventHostId) {
		this.eventHostId = eventHostId;
	}

	@Override
	public String toString() {
		return "EventHostBean [eventId=" + eventId + ", eventHostId=" + eventHostId + "]";
	}



}
