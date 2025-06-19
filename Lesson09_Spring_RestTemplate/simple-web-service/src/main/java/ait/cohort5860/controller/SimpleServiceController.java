package ait.cohort5860.controller;

import ait.cohort5860.dto.PersonDto;
import ait.cohort5860.dto.PersonFeedDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleServiceController {

    @GetMapping("/hello")
    public String hello(@RequestParam("title") String name) {
        return "Hello " + name;
    }

    @PostMapping
    public String hello(@RequestBody PersonDto person) {
        return "Hello " + person.getFirstName() + " " + person.getLastName();
    }

    @PostMapping("/feed")
    public PersonFeedDto personEating(@RequestBody PersonDto person) {
        return PersonFeedDto.builder()
                .fullName(person.getFirstName() + " " + person.getLastName())
                .food("Pizza")
                .food("Burger")
                .food("Fries")
                .build();
    }

    @PostMapping("/feed/{food}")
    public PersonFeedDto personEating(@RequestBody PersonDto person, @PathVariable String food) {
        return PersonFeedDto.builder()
                .fullName(person.getFirstName() + " " + person.getLastName())
                .food(food)
                .build();
    }
}
