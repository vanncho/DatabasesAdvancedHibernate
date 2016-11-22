package app.servicesImpl;

import app.entities.persons.Person;
import app.repositories.PersonRepository;
import app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void create(Person person) {
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public Person findPersonById(long id) {
        return this.personRepository.findById(id);
    }

    @Override
    public Person findPersonByName(String name) {
        return this.personRepository.findByName(name);
    }

    @Override
    public List<Person> findPersonsNonVictimsOfAnomalies() {
        return Collections.unmodifiableList(this.personRepository.findPersonsNonVictimsOfAnomalies());
    }
}
