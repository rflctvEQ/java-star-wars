package JavaStarWars.java_star_wars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSWController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
