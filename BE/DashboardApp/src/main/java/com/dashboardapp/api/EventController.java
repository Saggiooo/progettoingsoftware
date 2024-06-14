package com.dashboardapp.api;


import com.dashboardapp.models.Event;
import com.dashboardapp.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController // Annotation per definire che la classe risponderà tramite rest
@RequestMapping("/api/events") // Annotation per definire il path della classe
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/organizer/{organizerId}") //Get degli eventi in base all'organizzatore
    public List<Event> getEventsByOrganizer(@PathVariable String organizerId) {
        return eventService.getEventsByOrganizerId(organizerId);
    }

    @GetMapping("/{id}") //Get di un evento in base all'ID
    public Event getEventById(@PathVariable String id) {
        return eventService.getEventById(id);
    }
}
