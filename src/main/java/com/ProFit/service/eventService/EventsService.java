package com.ProFit.service.eventsService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.eventsBean.EventsBean;
import com.ProFit.dao.eventsCRUD.EventsDAO;

@Service
@Transactional
public class EventsService {

    @Autowired
    private EventsDAO eventsDAO;

    public List<EventsBean> getAllEvents() {
        return eventsDAO.findAll();
    }

    public EventsBean getEventById(String eventId) {
        return eventsDAO.findById(eventId).orElse(null);
    }

    public String saveEvent(EventsBean event) {
        eventsDAO.save(event);
        return event.getEventId();
    }

    public String removeEvent(String eventId) {
        eventsDAO.deleteById(eventId);
        return eventId;
    }
}
