package com.example.dashboard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recensioneId;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci un titolo della recensione")
    private String titolo;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci un testo della recensione")
    private String testo;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci un voto della recensione")
    private Integer voto;

    @Column(nullable = false)
    @NotBlank(message = "Nome dell'autore")
    private String autore;
}
