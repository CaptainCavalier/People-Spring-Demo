package uk.sky.people.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "Hello, World!";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String welcomeHome() {
        return "THIS IS THE HOME PAGE!!!!";
    }



}
