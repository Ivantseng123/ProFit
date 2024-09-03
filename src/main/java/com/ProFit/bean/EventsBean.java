package com.ProFit.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EventsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String eventId;
	private String eventName;
	private boolean isEventActive;
	private LocalDateTime eventStartDate;
	private LocalDateTime eventEndDate;
	private String eventDescription;
	private int eventAmount;
	private String eventLocation;
	private int eventParticipantMaximum;
	private String eventNote;

	public EventsBean() {
	}
	
	public EventsBean(String eventId, String eventName, boolean isEventActive, LocalDateTime eventStartDate,
			LocalDateTime eventEndDate, String eventDescription, int eventAmount, String eventLocation,
			int eventParticipantMaximum, String eventNote) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.isEventActive = isEventActive;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.eventDescription = eventDescription;
		this.eventAmount = eventAmount;
		this.eventLocation = eventLocation;
		this.eventParticipantMaximum = eventParticipantMaximum;
		this.eventNote = eventNote;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean isEventActive() {
		return isEventActive;
	}

	public void setEventActive(boolean isEventActive) {
		this.isEventActive = isEventActive;
	}

	public LocalDateTime getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(LocalDateTime eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public LocalDateTime getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(LocalDateTime eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public int getEventAmount() {
		return eventAmount;
	}

	public void setEventAmount(int eventAmount) {
		this.eventAmount = eventAmount;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public int getEventParticipantMaximum() {
		return eventParticipantMaximum;
	}

	public void setEventParticipantMaximum(int eventParticipantMaximum) {
		this.eventParticipantMaximum = eventParticipantMaximum;
	}

	public String getEventNote() {
		return eventNote;
	}

	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}

	@Override
	public String toString() {
		return "EventsBean [eventId=" + eventId + ", eventName=" + eventName + ", isEventActive=" + isEventActive
				+ ", eventStartDate=" + eventStartDate + ", eventEndDate=" + eventEndDate + ", eventDescription="
				+ eventDescription + ", eventAmount=" + eventAmount + ", eventLocation=" + eventLocation
				+ ", eventParticipantMaximum=" + eventParticipantMaximum + ", eventNote=" + eventNote + "]";
	}

	


}
