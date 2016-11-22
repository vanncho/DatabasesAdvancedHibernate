package app.entities.planets.dto.imports.json;

import java.io.Serializable;

public class PlanetImportJSONdto implements Serializable {

    private String name;

    private String sun;

    private String solarSystem;

    public PlanetImportJSONdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
