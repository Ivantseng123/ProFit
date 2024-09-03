package com.ProFit.controller.events;

import com.ProFit.bean.EventsBean;
import com.ProFit.dao.eventsCRUD.EventsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/events")
public class EventsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EventsDAO eventsDAO = new EventsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                request.setAttribute("event", new EventsBean());
                request.getRequestDispatcher("/eventsVIEW/eventForm.jsp").forward(request, response);
                break;
            case "edit":
                String eventId = request.getParameter("eventId");
                EventsBean event = eventsDAO.selectEventById(eventId);
                request.setAttribute("event", event);
                request.getRequestDispatcher("/eventsVIEW/eventForm.jsp").forward(request, response);
                break;
            case "delete":
                eventId = request.getParameter("eventId");
                eventsDAO.deleteEvent(eventId);
                response.sendRedirect("events?action=list");
                break;
            case "list":
            default:
                List<EventsBean> events = eventsDAO.selectAllEvents();
                request.setAttribute("events", events);
                request.getRequestDispatcher("/eventsVIEW/events.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        String eventName = request.getParameter("eventName");
        boolean isEventActive = Boolean.parseBoolean(request.getParameter("isEventActive"));
        LocalDateTime eventStartDate = LocalDateTime.parse(request.getParameter("eventStartDate"));
        LocalDateTime eventEndDate = LocalDateTime.parse(request.getParameter("eventEndDate"));
        String eventDescription = request.getParameter("eventDescription");
        int eventAmount = Integer.parseInt(request.getParameter("eventAmount"));
        String eventLocation = request.getParameter("eventLocation");
        int eventParticipantMaximum = Integer.parseInt(request.getParameter("eventParticipantMaximum"));
        String eventNote = request.getParameter("eventNote");

        EventsBean event = new EventsBean(eventId, eventName, isEventActive, eventStartDate, eventEndDate, eventDescription, eventAmount, eventLocation, eventParticipantMaximum, eventNote);
        if (eventsDAO.selectEventById(eventId) == null) {
            eventsDAO.insertEvent(event);
        } else {
            eventsDAO.updateEvent(event);
        }
        response.sendRedirect("events?action=list");
    }
}
