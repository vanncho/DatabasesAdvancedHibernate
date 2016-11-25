package app.services;

import app.entities.planets.Planet;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanetService {

    void create(Planet planet);

    Planet findPlanetByName(String name);

    List<Planet> findWithNoTeleportedPeople();
}
