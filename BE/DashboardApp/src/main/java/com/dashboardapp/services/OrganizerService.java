package com.dashboardapp.services;


import com.dashboardapp.models.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class OrganizerService {

    private final RestClient restClient;

    static final String uriBaseMock = "http://localhost:3000/api/organizer/";

    @Autowired
    public OrganizerService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Organizer getOrganizerById(String organizerId) {
        return restClient.get()
                .uri(uriBaseMock + organizerId)
                .retrieve()
                .body(Organizer.class);
    }
}
