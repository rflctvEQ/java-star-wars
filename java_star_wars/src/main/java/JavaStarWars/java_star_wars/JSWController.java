package JavaStarWars.java_star_wars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JSWController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/api/jedi")
    public String[] jedi() {

        String[] jediStrings = {"Yoda", "Obi-Wan", "Anakin"};

        return jediStrings;
    }

    @GetMapping("/api/people")
    public String people() {
        // TODO: create a list of uris for each page of people OR look into pagination tokens or whatever
        String uri = "https://www.swapi.tech/api/people";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }
}
