package com.dashboard.DashboardApp.controller;

import com.dashboard.DashboardApp.models.IdsResponse;
import com.dashboard.DashboardApp.models.Review;
import com.dashboard.DashboardApp.services.ReviewService;
import com.dashboard.DashboardApp.services.IdsService;
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
    private final IdsService idsService;

    @Autowired
    public ReviewController(ReviewService reviewService, IdsService idsService) {
        this.reviewService = reviewService;
        this.idsService = idsService;
    }

    // Endpoint che recupera gli id da Mockoon
    @GetMapping("/ids")
    public IdsResponse getIds(){
        return idsService.fetchIds();
    }

    //recupero le recensioni filtrate
    @GetMapping("/recensioni")
    public List<Review> getReviews(@RequestParam Integer eventId, @RequestParam(required = false) Integer generalRating, @RequestParam(defaultValue = "desc") String dateOrder){
        if(generalRating != null){
            //filtro per il rating impostato e per ordine data
            return reviewService.getReviewsByEventAndRating(eventId, generalRating, dateOrder);
        }
        // se il rating non è impostato filtro solo per data
        return reviewService.getReviewsByEvent(eventId, dateOrder);
    }

    // crea la review
    @PostMapping("/recensioni")
    public Mono<ResponseEntity<Review>> createReview(@RequestBody Review review) {
        return reviewService.saveReview(review)
                .map(savedReview -> ResponseEntity.status(HttpStatus.CREATED).body(savedReview))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    //aggiungo un endpoint per ottenere le stats delle recensioni
    @GetMapping("/recensioni/statistiche")
    public ResponseEntity<Map<String, Object>> getReviewStatistics(@RequestParam Integer eventId){
        //calcolo le stats delle recensioni per un determinato evento
        Map<String, Object> stats = reviewService.calculateReviewStatistics(eventId);

        //restituisco le stats al FE
        return ResponseEntity.ok(stats);
    }
}
