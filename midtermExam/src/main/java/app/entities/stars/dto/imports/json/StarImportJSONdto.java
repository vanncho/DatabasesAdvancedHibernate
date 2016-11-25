package app.entities.stars.dto.imports.json;

import java.io.Serializable;

public class StarImportJSONdto implements Serializable {

    private String name;

    private String solarSystem;

    public StarImportJSONdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
