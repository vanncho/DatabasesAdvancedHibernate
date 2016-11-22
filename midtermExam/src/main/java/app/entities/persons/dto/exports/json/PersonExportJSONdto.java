package app.entities.persons.dto.exports.json;

import java.io.Serializable;

public class PersonExportJSONdto implements Serializable {
    
    private String name;
    
    private String homePlanet;

    public PersonExportJSONdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }
}
