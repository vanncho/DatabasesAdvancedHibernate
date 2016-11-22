package app.servicesImpl;

import app.entities.planets.Planet;
import app.repositories.PlanetRepository;
import app.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(Planet planet) {
        this.planetRepository.saveAndFlush(planet);
    }

    @Override
    public Planet findPlanetByName(String name) {
        return this.planetRepository.findByName(name);
    }

    @Override
    public List<Planet> findWithNoTeleportedPeople() {
        return Collections.unmodifiableList(this.planetRepository.findWithNoTeleportedPeople());
    }
}
