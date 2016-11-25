package app.repositories;

import app.entities.persons.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    Person findByName(String name);

    @Query(value = "SELECT per FROM Person AS per LEFT JOIN per.anomalies AS av WHERE av.victims.size = 0")
    List<Person> findPersonsNonVictimsOfAnomalies();
}
