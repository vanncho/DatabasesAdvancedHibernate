package app.entities.persons.dto.imports.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimImportXMLDto implements Serializable {

    @XmlAttribute(name = "name", required = true)
    private String victim;

    public VictimImportXMLDto() {
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }
}
