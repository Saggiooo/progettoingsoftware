package com.dashboard.DashboardApp.models;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private Integer eventId;
    private Integer userId;
    private String title;
    private String text;
    private Integer generalRating;
    private Integer locationRating;
    private Integer staffRating;
    private Integer priceRating;
    private LocalDateTime date;

    // Costruttore, getter e setter
    public Review() {
        this.date = LocalDateTime.now(); // Imposta automaticamente la data alla creazione della recensione
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getReviewId() {
        return reviewId;
    }
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Integer getGeneralRating() {
        return generalRating;
    }
    public void setGeneralRating(Integer generalRating) {
        this.generalRating = generalRating;
    }

    public Integer getLocationRating() {
        return locationRating;
    }
    public void setLocationRating(Integer locationRating) {
        this.locationRating = locationRating;
    }

    public Integer getStaffRating() {
        return staffRating;
    }
    public void setStaffRating(Integer staffRating) {
        this.staffRating = staffRating;
    }

    public Integer getPriceRating() {
        return priceRating;
    }
    public void setPriceRating(Integer priceRating) {
        this.priceRating = priceRating;
    }
}
