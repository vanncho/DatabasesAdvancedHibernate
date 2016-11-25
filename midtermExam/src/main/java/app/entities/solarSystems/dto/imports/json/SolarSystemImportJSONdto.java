package app.entities.solarSystems.dto.imports.json;

import java.io.Serializable;

public class SolarSystemImportJSONdto implements Serializable {

    private String name;

    public SolarSystemImportJSONdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
