package com.dashboard.DashboardApp.controller;

import com.dashboard.DashboardApp.models.Review;
import com.dashboard.DashboardApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Recupero le recensioni filtrate
    @GetMapping("/recensioni")
    public List<Review> getReviews(@RequestParam Integer eventId, @RequestParam(required = false) Integer generalRating, @RequestParam(defaultValue = "desc") String dateOrder) {
        if (generalRating != null) {
            // Filtro per rating e per ordine data
            return reviewService.getReviewsByEventAndRating(eventId, generalRating, dateOrder);
        }
        // Se il rating non è impostato, filtro solo per data
        return reviewService.getReviewsByEvent(eventId, dateOrder);
    }

    // creo la recensione da salvare
    @PostMapping("/recensioni")
    public Mono<ResponseEntity<Review>> createReview(@RequestBody Review review) {
        // Aggiungiamo dei log per verificare i dati che arrivano
        System.out.println("Dati ricevuti nel BE: " + review.toString());

        return reviewService.saveReview(review)
                .map(savedReview -> ResponseEntity.status(HttpStatus.CREATED).body(savedReview))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Endpoint per ottenere le statistiche delle recensioni
    @GetMapping("/recensioni/statistiche")
    public ResponseEntity<Map<String, Object>> getReviewStatistics(@RequestParam Integer eventId) {
        // Calcolo le statistiche delle recensioni per un determinato evento
        Map<String, Object> stats = reviewService.calculateReviewStatistics(eventId);

        // Restituisco le statistiche al frontend
        return ResponseEntity.ok(stats);
    }

    // Metodo per cancellare una recensione
    @DeleteMapping("/recensioni/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Recensione cancellata con successo");
    }
}