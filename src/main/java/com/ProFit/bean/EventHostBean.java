package com.ProFit.bean;

import java.io.Serializable;

public class EventHostBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String eventId;
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
