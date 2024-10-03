package com.ProFit.controller.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ProFit.bean.eventsBean.EventsBean;
import com.ProFit.service.eventService.EventsService;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping
    public String listEvents(Model model) {
        List<EventsBean> events = eventsService.selectAllEvents();
        model.addAttribute("events", events);
        return "eventsVIEW/events";
    }

    @GetMapping("/new")
    public String newEvent(Model model) {
        model.addAttribute("event", new EventsBean());
        return "eventsVIEW/eventForm";
    }

    @GetMapping("/edit/{eventId}")
    public String editEvent(@PathVariable String eventId, Model model) {
        EventsBean event = eventsService.selectEventById(eventId);
        model.addAttribute("event", event);
        return "eventsVIEW/eventForm";
    }

    @GetMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        eventsService.deleteEvent(eventId);
        return "redirect:/events";
    }

    @PostMapping
    public String saveEvent(@ModelAttribute EventsBean event) {
        if (eventsService.selectEventById(event.getEventId()) == null) {
            eventsService.insertEvent(event);
        } else {
            eventsService.updateEvent(event);
        }
        return "redirect:/events";
    }
}