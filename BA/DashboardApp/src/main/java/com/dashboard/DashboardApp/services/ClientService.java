package com.dashboard.DashboardApp.services;

import com.dashboard.DashboardApp.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final WebClient webClient;

    @Autowired
    public ClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();
    }

    /**
     * Ottiene la lista dei clienti dall'endpoint `/api/partecipanti`.
     *
     * @return Lista di oggetti Client.
     */
    public List<Client> getClients() {
        try {
            return this.webClient.get()
                    .uri("/api/partecipanti")
                    .retrieve()
                    .bodyToFlux(Client.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            // Log dell'errore e ritorno di una lista vuota
            System.err.println("Errore nel recupero dei dati: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Ottiene il conteggio dei clienti per nazione.
     *
     * @return Mappa delle nazioni con il conteggio dei clienti.
     */
    public Map<String, Long> getNationStats() {
        List<Client> clients = this.getClients();
        return clients.stream()
                .collect(Collectors.groupingBy(Client::getNation, Collectors.counting()));
    }

    /**
     * Ottiene il conteggio dei clienti per sesso.
     *
     * @return Mappa dei sessi con il conteggio dei clienti.
     */
    public Map<String, Long> getSexStats() {
        List<Client> clients = this.getClients();
        return clients.stream()
                .collect(Collectors.groupingBy(client -> client.isSex() ? "male" : "female", Collectors.counting()));
    }

    /**
     * Restituisce il totale dei clienti.
     *
     * @return Numero totale dei clienti.
     */
    public int getTotalClients() {
        return this.getClients().size();
    }

    /**
     * Restituisce tutte le statistiche sui clienti.
     *
     * @return Mappa con le statistiche sui clienti.
     */
    public Map<String, Object> getClientStats() {
        Map<String, Long> nationStats = getNationStats();
        Map<String, Long> sexStats = getSexStats();
        int totalClients = getTotalClients();

        return Map.of(
                "totalClients", totalClients,
                "nationStats", nationStats,
                "sexStats", sexStats
        );
    }
}
