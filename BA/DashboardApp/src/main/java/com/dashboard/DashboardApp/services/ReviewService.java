package com.dashboard.DashboardApp.services;

import com.dashboard.DashboardApp.models.Review;
import com.dashboard.DashboardApp.models.IdsResponse;
import com.dashboard.DashboardApp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//componente Spring per il servizio
@Service
public class ReviewService {

    private final WebClient webClient;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(WebClient.Builder webClientBuilder, ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();

    }

    //recupera id utente e id evento da mockoon e popola il model review per poi salvarlo nel db

    public Mono<Review> saveReview(Review review) {
        return webClient.get()
                .uri("/api/ids")
                .retrieve()
                .bodyToMono(IdsResponse.class)
                .flatMap(idsResponse ->{
                    review.setUserId(idsResponse.getUserId());
                    review.setEventId(idsResponse.getEventId());
                    review.setDate(LocalDateTime.now());
                    return Mono.just(reviewRepository.save(review));
                        });

    }

    // Metodo per ottenere gli ID evento e utente da Mockoon
    public Mono<Map<String, Integer>> getReviewIds() {
        return webClient.get()
                .uri("/api/ids") // endpoint di Mockoon
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Integer>>() {}); // Specifica il tipo corretto
    }

    // Metodo per recuperare tutte le recensioni filtrate per rating e data
    public List<Review> getReviewsByEventAndRating(Integer eventId, Integer generalRating, String dateOrder) {
        if("asc".equalsIgnoreCase(dateOrder)) {
            return reviewRepository.findByEventIdAndGeneralRatingOrderByDateAsc(eventId, generalRating);
        } else{
            return reviewRepository.findByEventIdAndGeneralRatingOrderByDateDesc(eventId, generalRating);
        }
    }
    // Metodo per recuperare tutte le recensioni filtrate per data senza valutazione
    public List<Review> getReviewsByEvent(Integer eventId, String dateOrder) {
        if("asc".equalsIgnoreCase(dateOrder)) {
            return reviewRepository.findByEventIdOrderByDateAsc(eventId);
        } else{
            return reviewRepository.findByEventIdOrderByDateDesc(eventId);
        }
    }

    //Metodo per calcolare le statistiche delle recensioni e mostrare per ogni evento la distribuzione dei voti
    public Map<String, Object> calculateReviewStatistics(Integer eventId){
        Map<String, Object> stats = new HashMap<>();

        //recupero tutte le recensioni
        List<Review> reviews = reviewRepository.findByEventIdOrderByDateDesc(eventId);

        double averageRating = reviews.stream()
                .mapToInt(Review::getGeneralRating)
                .average()
                .orElse(0.0);

        //conto i voti per ogni livello di rating
        Map<Integer, Long> ratingCount = reviews.stream()
                .collect(Collectors.groupingBy(Review::getGeneralRating, Collectors.counting()));

        // Assicurati che ci siano valori per ogni livello di rating
        for (int i = 1; i <= 5; i++) {
            ratingCount.putIfAbsent(i, 0L);
        }


        stats.put("averageRating", averageRating);
        stats.put("ratingCount", ratingCount);
        return stats;
    }


}
