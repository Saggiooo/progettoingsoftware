package com.dasboardapp.controller;

import com.dasboardapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClientException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.dasboardapp.entity.User;
import com.dasboardapp.entity.UserList;
import com.dasboardapp.service.UserService;

@RestController
public class HelloWorldController {
    private final UserService userService;

    @Autowired
    public HelloWorldController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/callREST")
    public String sayHelloWorld() {
        return "[{ \"pippo\": 114 }]";
    }

    @GetMapping("/api/callPROVA")
    public String HelloWorld() {
        final String uri = "http://localhost:3000/api/callREST";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("/api/allData")
    public String provaHelloWorld() {
        final String uri = "http://localhost:3000/api/allData";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("/api/users")
    public List<User> users() {
        final String uri = "http://localhost:3000/api/users";
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<UserList> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<UserList>() {});

            if (response.getStatusCode() == HttpStatus.OK) {
                List<User> users = response.getBody();
                userService.processUsers(users);
                return users;
            }

        } catch (RestClientException rce) {
            // Handle error
        }

        return null;
    }
}