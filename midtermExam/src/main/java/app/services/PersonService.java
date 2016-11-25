package app.services;

import app.entities.persons.Person;

import java.util.List;

public interface PersonService {

    void create(Person person);

    Person findPersonById(long id);

    Person findPersonByName(String name);

    List<Person> findPersonsNonVictimsOfAnomalies();
}
