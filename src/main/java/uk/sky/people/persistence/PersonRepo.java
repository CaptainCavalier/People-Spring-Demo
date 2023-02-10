package uk.sky.people.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.sky.people.entities.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
}
