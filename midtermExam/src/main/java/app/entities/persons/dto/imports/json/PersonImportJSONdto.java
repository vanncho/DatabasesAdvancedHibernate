package app.entities.persons.dto.imports.json;

import java.io.Serializable;

public class PersonImportJSONdto implements Serializable {

    private long id;

    private String name;

    private String homePlanet;

    public PersonImportJSONdto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
