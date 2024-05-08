package com.example.dashboard.repositories;

import com.example.dashboard.entities.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProvaRepository extends JpaRepository<Prova, Long> {
    List<Prova> findByNome(String nome);

    @Query("SELECT p FROM Prova p WHERE p.descrizione LIKE %:keyword%")
    List<Prova> findByKeyword(String keyword);
}
