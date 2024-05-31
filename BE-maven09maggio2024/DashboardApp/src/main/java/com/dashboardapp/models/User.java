package com.dashboardapp.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String email;
    // ... altri campi...

    // getters and setters
}
