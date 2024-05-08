package com.example.dashboard.repositories;

import com.example.dashboard.models.MyTable;
import org.springframework.data.jpa.repository.JpaRepository;
/* Classe che definisce il repository (database)  */
public interface MyRepository extends JpaRepository<MyTable, Long> {

}
