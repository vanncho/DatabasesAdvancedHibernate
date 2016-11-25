package app.entities.anomalies.dto.exports.xml;

import app.entities.persons.dto.imports.xml.VictimImportXMLDto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anomaly")
public class AnomalyWithVictimImportXMLDto implements Serializable {

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimImportXMLDto> victimName;

    public AnomalyWithVictimImportXMLDto() {
        this.setVictimName(new ArrayList<>());
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimImportXMLDto> getVictimName() {
        return victimName;
    }

    public void setVictimName(List<VictimImportXMLDto> victimName) {
        this.victimName = victimName;
    }
}
