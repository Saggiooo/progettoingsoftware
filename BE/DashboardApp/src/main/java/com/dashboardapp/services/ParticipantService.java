package com.dashboardapp.services;


import com.dashboardapp.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class ParticipantService {

    private final RestClient restClient;

    static final String uriBaseMock = "http://localhost:3000/api/participants/";

    @Autowired
    public ParticipantService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Participant getParticipantById(String participantId) {
        return restClient.get()
                .uri(uriBaseMock + participantId)
                .retrieve()
                .body(Participant.class);
    }

    public List<Participant> getParticipantByEventId(String eventId) {
        return restClient.get()
                .uri(uriBaseMock + "event/" + eventId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Participant>>() {});
    }
}
