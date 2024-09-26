package com.ProFit.controller.events;

//import java.io.IOException;
//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProFit.bean.eventsBean.EventsBean;
import com.ProFit.dao.eventsCRUD.EventsDAO;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/events")

@Controller
@RequestMapping("/events")
public class EventsController{

	@Autowired
	private EventsDAO eventsDAO;
	
    @GetMapping
    public String getAllEvents(Model model) {
        List<EventsBean> events = eventsDAO.selectAllEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/get/{eventId}")
    public String getEventById(@PathVariable String eventId, Model model) {
        if (eventId != null) {
            EventsBean event = eventsDAO.selectEventById(eventId);
            model.addAttribute("event", event);
        }
        return "eventForm";
    }


    @PostMapping
    public String createEvent(EventsBean event) {
        if (event.getEventId() == null || event.getEventId().isEmpty()) {
            eventsDAO.insertEvent(event);
        } else {
            eventsDAO.updateEvent(event);
        }
        return "redirect:/events";
    }

    @GetMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        if (eventsDAO.selectEventById(eventId) != null) {
            eventsDAO.deleteEvent(eventId);
        }
        return "redirect:/events";
    }

    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     String action = request.getParameter("action");
    //     if (action == null) {
    //         action = "list";
    //     }

    //     switch (action) {
    //         case "new":
    //             request.setAttribute("event", new EventsBean());
    //             request.getRequestDispatcher("/eventsVIEW/eventForm.jsp").forward(request, response);
    //             break;
    //         case "edit":
    //             String eventId = request.getParameter("eventId");
    //             EventsBean event = eventsDAO.selectEventById(eventId);
    //             request.setAttribute("event", event);
    //             request.getRequestDispatcher("/eventsVIEW/eventForm.jsp").forward(request, response);
    //             break;
    //         case "delete":
    //             eventId = request.getParameter("eventId");
    //             eventsDAO.deleteEvent(eventId);
    //             response.sendRedirect("events?action=list");
    //             break;
    //         case "list":
    //         default:
    //             List<EventsBean> events = eventsDAO.selectAllEvents();
    //             request.setAttribute("events", events);
    //             request.getRequestDispatcher("/eventsVIEW/events.jsp").forward(request, response);
    //             break;
    //     }
    // }

    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     String eventId = request.getParameter("eventId");
    //     String eventName = request.getParameter("eventName");
    //     int isEventActive = Integer.parseInt(request.getParameter("isEventActive"));
    //     Integer eventMajor = Integer.parseInt(request.getParameter("eventMajor"));
    //     LocalDateTime eventStartDate = LocalDateTime.parse(request.getParameter("eventStartDate"));
    //     LocalDateTime eventEndDate = LocalDateTime.parse(request.getParameter("eventEndDate"));
    //     LocalDateTime eventPartStartDate = LocalDateTime.parse(request.getParameter("eventPartStartDate"));
    //     LocalDateTime eventPartEndDate = LocalDateTime.parse(request.getParameter("eventPartEndDate"));
    //     int eventAmount = Integer.parseInt(request.getParameter("eventAmount"));
    //     String eventLocation = request.getParameter("eventLocation");
    //     int eventParticipantMaximum = Integer.parseInt(request.getParameter("eventParticipantMaximum"));
    //     String eventDescription = request.getParameter("eventDescription");
    //     String eventNote = request.getParameter("eventNote");

    //     EventsBean event = new EventsBean(eventId, eventName, isEventActive, eventMajor, eventStartDate, eventEndDate, eventPartStartDate, eventPartEndDate, eventAmount, eventLocation, eventParticipantMaximum, eventDescription, eventNote);        if (eventsDAO.selectEventById(eventId) == null) {
    //         eventsDAO.insertEvent(event);
    //     } else {
    //         eventsDAO.updateEvent(event);
    //     }
    //     response.sendRedirect("events?action=list");
    // }
}
