package com.dashboard.DashboardApp.controller;

import com.dashboard.DashboardApp.models.Client;
import com.dashboard.DashboardApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/clientinfo")
    public Mono<ResponseEntity<List<Client>>> getClientInfo() {
        return Mono.fromCallable(() -> clientService.getClients())
                .map(clients -> {
                    if (clients.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(clients);
                });
    }

    /**
     * Restituisce le statistiche per nazione
     */
    @GetMapping("/nationinfo")
    public Mono<ResponseEntity<Map<String, Long>>> getNationInfo() {
        return Mono.fromCallable(() -> clientService.getClients())
                .map(clients -> {
                    Map<String, Long> nationStats = clients.stream()
                            .collect(Collectors.groupingBy(Client::getNation, Collectors.counting()));
                    return ResponseEntity.ok(nationStats);
                });
    }

    /**
     * Restituisce le statistiche per sesso
     */
    @GetMapping("/sexinfo")
    public Mono<ResponseEntity<Map<String, Long>>> getSexInfo() {
        return Mono.fromCallable(() -> clientService.getClients())
                .map(clients -> {
                    Map<String, Long> sexStats = clients.stream()
                            .collect(Collectors.groupingBy(client -> client.isSex() ? "male" : "female", Collectors.counting()));
                    return ResponseEntity.ok(sexStats);
                });
    }
}
