package com.example.dashboard.api;

import com.example.dashboard.models.MyTable;
import com.example.dashboard.services.MyService;
import com.example.dashboard.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/api") /* Annotation per definire il path della classe  */
public class MainController {
    @Autowired
    MyService myService;
    @RequestMapping("/testMysql") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<MyTable> testMysql() {
        return myService.getAll();
    }

    @RequestMapping("/testWithElements") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<MyTable> addElements() {

        /* Chiamata a un servizio che ritorna inserisce 3 dati e ritorna il db */
        return myService.addElements();
    }

    @RequestMapping("/callREST") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public String callRest() {
        return TemplateRestConsumer.callREST("callREST", null, true);
    }
}
