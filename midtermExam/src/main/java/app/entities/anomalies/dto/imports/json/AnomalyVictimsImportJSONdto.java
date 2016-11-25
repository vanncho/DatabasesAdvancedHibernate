package app.entities.anomalies.dto.imports.json;

import java.io.Serializable;

public class AnomalyVictimsImportJSONdto implements Serializable {

    private long id;

    private String person;

    public AnomalyVictimsImportJSONdto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
