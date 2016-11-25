package app.entities.anomalies.dto.exports.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyExportXMLDto implements Serializable {

    @XmlAttribute(name = "id", required = true)
    private long id;

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimExportXMLDto> victims;

    public AnomalyExportXMLDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<VictimExportXMLDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimExportXMLDto> victims) {
        this.victims = victims;
    }
}
