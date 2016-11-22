package app.services;

import app.entities.solarSystems.SolarSystem;

import java.util.List;

public interface SolarSystemService {

    void create(SolarSystem solarSystem);

    SolarSystem findSolarSystemByName(String name);
}
