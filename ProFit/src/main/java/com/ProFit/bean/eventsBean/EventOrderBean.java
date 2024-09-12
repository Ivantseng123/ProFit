package com.ProFit.bean.eventsBean;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ProFit.bean.usersBean.*;

import jakarta.persistence.*;

@Entity
@Table(name = "event_order")
public class EventOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String eventOrderId;
	private int eventOrderAmount;
	private boolean isEventOrderActive;
	private LocalDateTime eventParticipantDate;
	private String eventParticipantNote;

	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private EventsBean event;

	@ManyToOne
	@JoinColumn(name = "event_participant_id", referencedColumnName = "user_id")
	private Users participant;

	public EventOrderBean() {
	}

	public EventOrderBean(String eventOrderId, int eventOrderAmount, boolean isEventOrderActive, String eventId,
			int eventParticipantId, LocalDateTime eventParticipantDate, String eventParticipantNote) {
		super();
		this.eventOrderId = eventOrderId;
		this.eventOrderAmount = eventOrderAmount;
		this.isEventOrderActive = isEventOrderActive;
		this.eventParticipantDate = eventParticipantDate;
		this.eventParticipantNote = eventParticipantNote;
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
		return event.getEventId();
	}

	public int getEventParticipantId() {
		return participant.getUser_id();
	}

	public LocalDateTime getEventParticipantDate() {
		return eventParticipantDate;
	}

	public void setEventParticipantDate(LocalDateTime eventParticipantDate) {
		this.eventParticipantDate = eventParticipantDate;
	}

	public String getEventParticipantNote() {
		return eventParticipantNote;
	}

	public void setEventParticipantNote(String eventParticipantNote) {
		this.eventParticipantNote = eventParticipantNote;
	}

	@Override
	public String toString() {
		return "EventOrderBean [eventOrderId=" + eventOrderId + ", eventOrderAmount=" + eventOrderAmount
				+ ", isEventOrderActive=" + isEventOrderActive + ", eventId=" + this.getEventId() + ", eventParticipantId="
				+ this.getEventParticipantId() + ", eventParticipantDate=" + eventParticipantDate + ", eventParticipantNote="
				+ eventParticipantNote + "]";
	}

}
