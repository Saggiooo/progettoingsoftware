package com.example.dashboard.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/* Modello della taballa
 * Le annotation indicano che questa classe è un entity bean,
 * mappa una tabella che ha un nome fisico "my_table",
 * che i costruttori sono generici e auto creati dal plugin lombok,
 * e che lombok creerà anche tutti i getter e setter */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {
    private int numeroTotalePartecipanti;
    private double mediaRecensioni;
    // Altre variabili...
}
