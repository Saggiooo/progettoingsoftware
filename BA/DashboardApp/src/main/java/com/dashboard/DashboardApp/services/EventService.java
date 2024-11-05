package com.dashboard.DashboardApp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EventService {

    private final WebClient webClient;

    public EventService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();
    }

    public Mono<Object> getEvents() {
        return webClient.get()
                .uri("/api/events")
                .retrieve()
                .bodyToMono(Object.class);
    }
}
