package app.repositories;

import app.domains.models.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends CrudRepository<Town, Integer> {

    Town findByName(String name);
}
