package com.dashboard.DashboardApp.controller;

import com.dashboard.DashboardApp.services.DashboardService;
import com.dashboard.DashboardApp.models.Eventinfo;
import com.dashboard.DashboardApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/eventinfo")
    public Mono<ResponseEntity<List<Eventinfo>>> getEventinfo() {
        return Mono.just(dashboardService.getEventinfo())
                .map(events -> events.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(events));
    }

    // Nuovo endpoint /api/statsinfo per ottenere le statistiche
    // Nuovo endpoint /api/statsinfo per ottenere le statistiche
    @GetMapping("/statsinfo")
    public Mono<ResponseEntity<Map<String, Object>>> getStatsInfo() {
        return Mono.just(dashboardService.getEventinfo())
                .map(events -> {
                    int partecipantiTotali = events.stream()
                            .mapToInt(Eventinfo::getPartecipanti)  // Supponiamo che `Eventinfo` abbia un metodo `getPartecipanti()`
                            .sum();

                    // Calcolare la media dei partecipanti
                    double mediaPartecipanti = events.stream()
                            .mapToInt(Eventinfo::getPartecipanti)
                            .average()
                            .orElse(0);  // Restituisce 0 se la lista è vuota


                    // Trovare l'evento con il massimo numero di partecipanti
                    String eventoConMassimiPartecipanti = events.stream()
                            .max((e1, e2) -> Integer.compare(e1.getPartecipanti(), e2.getPartecipanti()))
                            .map(Eventinfo::getEventName)  // Supponiamo che `Eventinfo` abbia il metodo `getNomeEvento()`
                            .orElse("N/A");  // Restituisce "N/A" se la lista è vuota

                    // Trovare l'evento con il minimo numero di partecipanti
                    String eventoConMinimiPartecipanti = events.stream()
                            .min((e1, e2) -> Integer.compare(e1.getPartecipanti(), e2.getPartecipanti()))
                            .map(Eventinfo::getEventName)  // Supponiamo che `Eventinfo` abbia il metodo `getNomeEvento()`
                            .orElse("N/A");  // Restituisce "N/A" se la lista è vuota



                    // Aggiungi altre statistiche che desideri qui
                    Map<String, Object> stats = Map.of(
                            "partecipantitotali", partecipantiTotali,
                            "mediaPartecipanti", mediaPartecipanti,
                            "eventoConMassimiPartecipanti", eventoConMassimiPartecipanti,
                            "eventoConMinimiPartecipanti", eventoConMinimiPartecipanti
                    );

                    return ResponseEntity.ok(stats);
                });
    }
}
