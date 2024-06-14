package com.dashboardapp.api;


import com.dashboardapp.models.Organizer;
import com.dashboardapp.services.OrganizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // Annotation per definire che la classe risponderà tramite rest
@RequestMapping("/api/organizer") // Annotation per definire il path della classe
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping("/{id}") //Get di un organizzatore in base al suo ID
    public Organizer getOrganizerById(@PathVariable String id) {
        return organizerService.getOrganizerById(id);
    }

}
