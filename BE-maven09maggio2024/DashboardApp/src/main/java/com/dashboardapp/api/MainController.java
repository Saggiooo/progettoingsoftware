package com.dashboardapp.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.dashboardapp.models.User;
import com.dashboardapp.models.UserList;
import com.dashboardapp.services.UserService;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/api") /* Annotation per definire il path della classe  */
public class MainController {
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
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/callREST")
    @ApiOperation(value = "Get Hello World message", notes = "Returns a simple Hello World message")
    public ResponseEntity<String> sayHelloWorld() {
        Logger logger = LoggerFactory.getLogger(MainController.class);
        logger.info("Executing sayHelloWorld method");
        try {
            // Method logic here
            return ResponseEntity.ok("[{ \"pippo\": 114 }]");
        } catch (Exception e) {
            logger.error("An error occurred in sayHelloWorld method: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }

    @GetMapping("/callPROVA")
    public String HelloWorld() {
        final String uri = "http://localhost:3000/api/callREST";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("/allData")
    public String provaHelloWorld() {
        final String uri = "http://localhost:3000/api/allData";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("/users")
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