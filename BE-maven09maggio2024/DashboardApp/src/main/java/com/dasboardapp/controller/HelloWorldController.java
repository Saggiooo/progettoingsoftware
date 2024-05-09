package com.dasboardapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {


        @GetMapping("/api/callREST")
        public String sayHelloWorld(){
            return "[{ \"pippo\": 114 }]";
        }

        @GetMapping("/api/callPROVA")
        public String HelloWorld(){
        final String uri = "http://localhost:3000/api/callREST";

        RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForObject(uri, String.class);
    }
}
