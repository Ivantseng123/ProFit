package com.ProFit.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EventOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String eventOrderId;
	private int eventOrderAmount;
	private boolean isEventOrderActive;
	private String eventId;
	private int eventParticipantId;
	private LocalDateTime eventParticipantDate;
	private String eventParticipantData;
	
	public EventOrderBean() {
	}

	public EventOrderBean(String eventOrderId, int eventOrderAmount, boolean isEventOrderActive, String eventId,
			int eventParticipantId, LocalDateTime eventParticipantDate, String eventParticipantData) {
		super();
		this.eventOrderId = eventOrderId;
		this.eventOrderAmount = eventOrderAmount;
		this.isEventOrderActive = isEventOrderActive;
		this.eventId = eventId;
		this.eventParticipantId = eventParticipantId;
		this.eventParticipantDate = eventParticipantDate;
		this.eventParticipantData = eventParticipantData;
	}

	public String getEventOrderId() {
		return eventOrderId;
	}

	public void setEventOrderId(String eventOrderId) {
		this.eventOrderId = eventOrderId;
	}

	public int getEventOrderAmount() {
		return eventOrderAmount;
	}

	public void setEventOrderAmount(int eventOrderAmount) {
		this.eventOrderAmount = eventOrderAmount;
	}

	public boolean isEventOrderActive() {
		return isEventOrderActive;
	}

	public void setEventOrderActive(boolean isEventOrderActive) {
		this.isEventOrderActive = isEventOrderActive;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getEventParticipantId() {
		return eventParticipantId;
	}

	public void setEventParticipantId(int eventParticipantId) {
		this.eventParticipantId = eventParticipantId;
	}

	public LocalDateTime getEventParticipantDate() {
		return eventParticipantDate;
	}

	public void setEventParticipantDate(LocalDateTime eventParticipantDate) {
		this.eventParticipantDate = eventParticipantDate;
	}

	public String getEventParticipantData() {
		return eventParticipantData;
	}

	public void setEventParticipantData(String eventParticipantData) {
		this.eventParticipantData = eventParticipantData;
	}

	@Override
	public String toString() {
		return "EventOrderBean [eventOrderId=" + eventOrderId + ", eventOrderAmount=" + eventOrderAmount
				+ ", isEventOrderActive=" + isEventOrderActive + ", eventId=" + eventId + ", eventParticipantId="
				+ eventParticipantId + ", eventParticipantDate=" + eventParticipantDate + ", eventParticipantData="
				+ eventParticipantData + "]";
	}
	
}
