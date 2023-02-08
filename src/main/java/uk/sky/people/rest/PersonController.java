package uk.sky.people.rest;

import org.springframework.web.bind.annotation.*;
import uk.sky.people.entities.Person;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private List<Person> people = new ArrayList<>();


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "Hello, World!";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String welcomeHome() {
        return "THIS IS THE HOME PAGE!!!!";
    }

    @PostMapping("/create")
    public Person addPerson(@RequestBody Person person) { // pulls person from the body of the request
        this.people.add(person); // add a new person to the list
        return this.people.get(this.people.size() - 1); // return the last person in the list
    }

    @GetMapping("/getAll")
    public List<Person> getAll() {
        return this.people;
    }


    @GetMapping("/get/{id}")
    public Person getPerson(@PathVariable int id) {  //pulls id from the path (url)
        System.out.println("id: " + id);
        return this.people.get(id);
    }

    @PatchMapping("/update/{id}")
    public Person updatePerson(@PathVariable int id, @PathParam("name") String name, @PathParam("age") Integer age, @PathParam("job") String job) {
        Person old = this.people.get(id);

        if (name != null) old.setName(name);
        if (age != null) old.setAge(age);
        if (job != null) old.setJob(job);


        return old;

    }


    @DeleteMapping("/remove/{id}")
    public Person removePerson(@PathVariable int id) {
        return this.people.remove(id);
    }



}
