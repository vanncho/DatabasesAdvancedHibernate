package app.repositories;

import app.entities.planets.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Planet findByName(String name);

    @Query(value = "SELECT p FROM Planet AS p LEFT JOIN p.anomalies AS a WHERE a.originPlanet IS NULL")
    List<Planet> findWithNoTeleportedPeople();
}
