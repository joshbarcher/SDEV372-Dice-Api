package edu.greenriver.sdev.dicerollingapi.controllers;

import edu.greenriver.sdev.dicerollingapi.model.UriDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IndexController
{
    @GetMapping
    public ResponseEntity<List<UriDescription>> getApiDescription()
    {
        return new ResponseEntity<>(List.of(
            new UriDescription("http://localhost:8080", Map.of(
                "GET", "Provides rudimentary documentation of the api."
            )),
            new UriDescription("http://localhost:8080/dice", Map.of(
                "GET", "Returns all dice resources.",
                "POST", "Adds a new dice resource with color (string) and sides (integer) attributes.",
                "PUT", "Updates an existing dice resource with new color (string) and sides (integer) attributes."
            )),
            new UriDescription("http://localhost:8080/dice/{diceId}", Map.of(
                "GET", "Returns an existing dice resource with the given id.",
                "DELETE", "Deletes an existing dice resource with the given id."
            )),
            new UriDescription("http://localhost:8080/rolls", Map.of(
                "GET", "Returns all dice roll resources.",
                "PUT", "Updates an existing dice roll resource with a new result (integer)."
            )),
            new UriDescription("http://localhost:8080/rolls/{rollId}", Map.of(
                "GET", "Returns the dice roll with the given id.",
                "POST", "Adds a new dice roll resource with result (integer) attribute.",
                "DELETE", "Deletes an existing dice roll resource with the given id."
            )),
            new UriDescription("http://localhost:8080/bags", Map.of(
                "GET", "Returns all bag resources.",
                "POST", "Adds a new bag resource with materials (string) and maxCapacity (integer) attributes.",
                "PUT", "Updates an existing bag resource with new materials (string) and maxCapacity (integer) attributes."
            )),
            new UriDescription("http://localhost:8080/bags/{bagId}", Map.of(
                "GET", "Returns an existing bag resource with the given id.",
                "DELETE", "Deletes an existing bag resource with the given id."
            )),
            new UriDescription("http://localhost:8080/bags/{bagId}/dice", Map.of(
                "GET", "Returns all dice resources in the specified bag resource."
            )),
            new UriDescription("http://localhost:8080/bags/{bagId}/dice/{diceId}", Map.of(
                "POST", "Adds the specified dice resource to the bag resource with the matching id."
            )),
            new UriDescription("http://localhost:8080/bags/{bagId}/roll", Map.of(
                "POST", "Creates a new roll resource for each dice in the specified bag."
            ))
        ), HttpStatus.OK);
    }
}
