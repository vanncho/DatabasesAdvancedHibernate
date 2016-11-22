package app.entities.planets.dto.exports.json;

import java.io.Serializable;

public class PlanetExportJSONdto implements Serializable {
    
    private String name;

    public PlanetExportJSONdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
