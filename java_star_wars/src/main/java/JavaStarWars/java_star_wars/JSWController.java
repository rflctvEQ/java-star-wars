package JavaStarWars.java_star_wars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import JavaStarWars.utils.entities.ResponseObject;

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
    public ResponseObject  people() {
        try {
            // array of all endpoints needed to hit
            String[] uris = { 
                "https://www.swapi.tech/api/people", 
                "https://www.swapi.tech/api/people?page=2&limit=10", 
                "https://www.swapi.tech/api/people?page=3&limit=10", 
                "https://www.swapi.tech/api/people?page=4&limit=10",
                "https://www.swapi.tech/api/people?page=5&limit=10",
                "https://www.swapi.tech/api/people?page=6&limit=10",
                "https://www.swapi.tech/api/people?page=7&limit=10",
                "https://www.swapi.tech/api/people?page=8&limit=10",
                "https://www.swapi.tech/api/people?page=9&limit=10"
            };
    
            RestTemplate restTemplate = new RestTemplate();
            
            // ResponseObject is a custom class found in ./utils/entities
            ResponseObject results = new ResponseObject();
            
            for (String uri : uris) {
                // get one page list of people from SWAPI 
                String response = restTemplate.getForObject(uri, String.class);
                // convert resulting String into JSON
                JSONObject responseJSON = new JSONObject(response);
                // extract just the array of people
                JSONArray peopleArray = responseJSON.getJSONArray("results");
    
                // add new array of people to results to be returned
                results.responseList.add(peopleArray.toString());
            }
    
            return results;
        } catch (JSONException e) {
            ResponseObject errorResponse = new ResponseObject();
            errorResponse.jsonException = e;

            return errorResponse;
        }
    }
}
