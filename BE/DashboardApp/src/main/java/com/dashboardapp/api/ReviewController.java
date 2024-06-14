package com.dashboardapp.api;


import com.dashboardapp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // Annotation per definire che la classe risponderà tramite rest
@RequestMapping("/api/reviews") // Annotation per definire il path della classe
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

}


    /*@Autowired
    MyService myService;
    @RequestMapping("/testMysql") // Annotation per definire il path del metodo (relativo alla classe)
    /*public List<MyTable> testMysql() {
        return myService.getAll();
    }

    @RequestMapping("/testWithElements") // Annotation per definire il path del metodo (relativo alla classe)
    public List<MyTable> addElements() {

        // Chiamata a un servizio che ritorna inserisce 3 dati e ritorna il db
        return myService.addElements();
    }

    @RequestMapping("/callREST") // Annotation per definire il path del metodo (relativo alla classe)
    public String callRest() {
        return TemplateRestConsumer.callREST("helloMock", null, true);
    }*/