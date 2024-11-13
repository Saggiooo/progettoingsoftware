package com.dashboard.DashboardApp.services;

import com.dashboard.DashboardApp.models.IdsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IdsService {
    private final WebClient webClient;

    public IdsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://localhost:3000").build();  //URL Mockoon
    }

    public IdsResponse fetchIds(){
        return webClient.get()
                .uri("/api/ids")
                .retrieve()
                .bodyToMono(IdsResponse.class)
                .block();
    }
}
