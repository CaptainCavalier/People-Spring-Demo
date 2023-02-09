package uk.sky.people.service;

import uk.sky.people.entities.Person;

import java.util.List;

public interface PersonService {  // interfaces cannot be instantiated because they are abstract

    public abstract Person createPerson(Person p);

    Person getById(int id);

    List<Person> getAll();

    Person update(int id, String name, Integer age, String job);

    Person remove(int id);
}
