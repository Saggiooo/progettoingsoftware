package com.dashboard.DashboardApp.services;

import com.dashboard.DashboardApp.models.Eventinfo;
import com.dashboard.DashboardApp.models.Review;
import com.dashboard.DashboardApp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Componente Spring per il servizio
@Service
public class DashboardService {

    private final WebClient webClient;
    private final ReviewRepository reviewRepository;

    @Autowired
    public DashboardService(WebClient.Builder webClientBuilder, ReviewRepository reviewRepository) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();
        this.reviewRepository = reviewRepository;
    }

    // Metodo per ottenere gli eventi dal client Web
    public List<Eventinfo> getEventinfo(){
        return this.webClient.get()
                .uri("/api/eventinfo")
                .retrieve()
                .bodyToFlux(Eventinfo.class)
                .collectList()
                .block();
    }




    // Metodo per calcolare i partecipanti totali
    public int getTotalParticipants() {
        List<Eventinfo> events = this.getEventinfo();
        return events.stream()
                .mapToInt(Eventinfo::getPartecipanti)  // Supponiamo che `Eventinfo` abbia un metodo `getPartecipanti()`
                .sum();
    }

    // Metodo per calcolare la media dei partecipanti
    public double getAverageParticipants() {
        List<Eventinfo> events = this.getEventinfo();
        return events.stream()
                .mapToInt(Eventinfo::getPartecipanti)
                .average()
                .orElse(0.0); // Restituisce 0 se non ci sono eventi
    }

    // Metodo per ottenere l'evento con il massimo e minimo numero di partecipanti
    public Map<String, String> getEventWithMaxAndMinParticipants() {
        List<Eventinfo> events = this.getEventinfo();
        Eventinfo maxEvent = events.stream()
                .max(Comparator.comparingInt(Eventinfo::getPartecipanti))
                .orElse(null);

        Eventinfo minEvent = events.stream()
                .min(Comparator.comparingInt(Eventinfo::getPartecipanti))
                .orElse(null);

        Map<String, String> eventNames = new HashMap<>();
        eventNames.put("eventoConMassimiPartecipanti", maxEvent != null ? maxEvent.getEventName() : "N/A");
        eventNames.put("eventoConMinimiPartecipanti", minEvent != null ? minEvent.getEventName() : "N/A");

        return eventNames;
    }

    // Metodo che restituisce tutte le statistiche
    public Map<String, Object> getStatsInfo() {
        int partecipantiTotali = getTotalParticipants();
        double mediaPartecipanti = getAverageParticipants();
        Map<String, String> maxMinEvents = getEventWithMaxAndMinParticipants();

        Map<String, Object> stats = new HashMap<>();
        stats.put("partecipantitotali", partecipantiTotali);
        stats.put("mediaPartecipanti", mediaPartecipanti);
        stats.put("eventoConMassimiPartecipanti", maxMinEvents.get("eventoConMassimiPartecipanti"));
        stats.put("eventoConMinimiPartecipanti", maxMinEvents.get("eventoConMinimiPartecipanti"));
        stats.put("otherData", "Da definire");

        return stats;
    }
}
