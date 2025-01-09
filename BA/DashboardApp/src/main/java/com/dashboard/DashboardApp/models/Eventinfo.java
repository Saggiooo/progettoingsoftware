package com.dashboard.DashboardApp.models;

import jakarta.persistence.*;

@Entity
public class Eventinfo {

    private Integer eventId;
    private String eventName;
    private Integer partecipanti;
    private Integer costoBiglietto;

    @Id
    private Long id;

    // Getter e Setter per id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per eventId
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    // Getter e Setter per partecipanti
    public Integer getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(Integer partecipanti) {
        this.partecipanti = partecipanti;
    }

    // Getter e Setter per costoBiglietto
    public Integer getCostoBiglietto() {
        return costoBiglietto;
    }

    public void setCostoBiglietto(Integer costoBiglietto) {
        this.costoBiglietto = costoBiglietto;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}

