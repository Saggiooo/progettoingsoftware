package com.dasboardapp.service;

import com.dasboardapp.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    // Metodo per elaborare i dati
    public void processUsers(List<User> users){
        for(User user : users) {
            // Elaborazione dei dati utente
            System.out.println("User ID: " + user.getId());
            System.out.println("User Name: " + user.getName());
            System.out.println("User Email: " + user.getEmail());
            // ... altre elaborazioni di dati ...
        }
    }
}
