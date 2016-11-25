package app.servicesImpl;

import app.entities.solarSystems.SolarSystem;
import app.repositories.SolarSystemRepository;
import app.services.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarSystemServiceImpl implements SolarSystemService {

    private final SolarSystemRepository solarSystemRepository;

    @Autowired
    public SolarSystemServiceImpl(SolarSystemRepository solarSystemRepository) {
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void create(SolarSystem solarSystem) {
        this.solarSystemRepository.saveAndFlush(solarSystem);
    }

    @Override
    public SolarSystem findSolarSystemByName(String name) {
        return this.solarSystemRepository.findByName(name);
    }
}
