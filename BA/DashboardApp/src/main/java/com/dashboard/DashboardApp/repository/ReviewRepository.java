package com.dashboard.DashboardApp.repository;
import com.dashboard.DashboardApp.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//annotazione che definisce l'interfaccia come componente Spring per accedere al DB
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByEventIdOrderByDateDesc(Integer eventId);
    List<Review> findByEventIdOrderByDateAsc(Integer eventId);
    List<Review> findByEventIdAndGeneralRatingOrderByDateDesc(Integer eventId, Integer generalRating);
    List<Review> findByEventIdAndGeneralRatingOrderByDateAsc(Integer eventId, Integer generalRating);
}
