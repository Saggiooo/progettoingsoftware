package com.dashboardapp.services;


import com.dashboardapp.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class EventService {

    private final RestClient restClient;

    static final String uriBaseMock = "http://localhost:3000/api/events/";

    @Autowired
    public EventService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Event> getEventsByOrganizerId(String organizerId) { //Il metodo restituisce una lista di Eventi in base all'organizzatore
        return restClient.get().uri(uriBaseMock + "organizer/" + organizerId)//definizione dell'endpoint simulato da mockoon
                .retrieve().body(new ParameterizedTypeReference<List<Event>>() {
                }); //deserializza il corpo della risposta HTTP in una lista di eventi
    }

    public Event getEventById(String eventId) { //Il metodo restituisce un Evento in base all'ID
        return restClient.get().uri(uriBaseMock + eventId)//definizione dell'endpoint simulato da mockoon
                .retrieve().body(Event.class); //deserializza il corpo della risposta HTTP in un evento
    }
}
