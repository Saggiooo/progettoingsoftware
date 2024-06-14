package com.dashboardapp.api;


import com.dashboardapp.models.Participant;
import com.dashboardapp.services.ParticipantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController // Annotation per definire che la classe risponderà tramite rest
@RequestMapping("/api/participants") // Annotation per definire il path della classe
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/event/{eventId}") //Get dei partecipanti ad uno specifico evento
    public List<Participant> getParticipantByEventId(@PathVariable String eventId) {
        return participantService.getParticipantByEventId(eventId);
    }

    @GetMapping("/{id}") //Get di un partecipante in base al suo ID
    public Participant getParticipantById(@PathVariable String id) {
        return participantService.getParticipantById(id);
    }
}