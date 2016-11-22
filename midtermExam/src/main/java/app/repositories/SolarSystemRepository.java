package app.repositories;

import app.entities.solarSystems.SolarSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarSystemRepository extends JpaRepository<SolarSystem, Long> {

    SolarSystem findByName(String name);
}
