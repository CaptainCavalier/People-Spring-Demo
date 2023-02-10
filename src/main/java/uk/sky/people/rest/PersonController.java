package uk.sky.people.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.sky.people.dto.PersonDTO;
import uk.sky.people.dto.PersonReqDTO;
import uk.sky.people.entities.Person;
import uk.sky.people.service.PersonService;
import uk.sky.people.service.PersonServiceList;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

   // the service variable is a dependency
    // @Autowired -> also injects the dependency but isn't as good as constructor injection

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "Hello, World!";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String welcomeHome() {
        return "THIS IS THE HOME PAGE!!!!";
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonReqDTO person) { // pulls person from the body of the request
        Person toCreate = new Person(person.getFullName(), person.getOldNess(), person.getOccupation(), person.getNotNiNumber());
        Person created  = this.service.createPerson(toCreate);

        PersonDTO dto = new PersonDTO(created.getName(), created.getAge(), created.getJob());

        return new ResponseEntity<PersonDTO>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public List<PersonDTO> getAll() {
        List<PersonDTO> dtos = new ArrayList<>();
        List<Person> found = this.service.getAll();
//        standard for loop:
//        for (int i = 0; i < found.size(); i++) {
//            Person person = found.get(i);
//            PersonDTO dto = new PersonDTO(person.getName(), person.getAge(), person.getJob());
//            dtos.add(dto);
//        }

//        for each Person(object)in person(list) in found
        for (Person person : found){

           PersonDTO dto = new PersonDTO(person.getName(), person.getAge(), person.getJob());
           dtos.add(dto);

        }

        return dtos;
    }


    @GetMapping("/get/{id}")
    public PersonDTO getPerson(@PathVariable int id) {  //pulls id from the path (url)
        Person found = this.service.getById(id);

        PersonDTO dto = new PersonDTO(found.getName(), found.getAge(), found.getJob());

        return dto;
    }

    @PatchMapping("/update/{id}")
    public PersonDTO updatePerson(@PathVariable int id, @PathParam("name") String name, @PathParam("age") Integer age, @PathParam("job") String job) {
        Person updated = this.service.update(id, name, age, job);

        PersonDTO dto = new PersonDTO(updated.getName(), updated.getAge(), updated.getJob());

        return dto;
    }


    @DeleteMapping("/remove/{id}")
    public PersonDTO removePerson(@PathVariable int id) {
        Person remove = this.service.remove(id);

        PersonDTO dto = new PersonDTO(remove.getName(), remove.getAge(), remove.getJob());

        return dto;
    }



}
