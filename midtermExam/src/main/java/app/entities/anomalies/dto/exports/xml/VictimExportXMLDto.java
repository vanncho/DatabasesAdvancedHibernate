package app.entities.anomalies.dto.exports.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name = "victim")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimExportXMLDto implements Serializable {

    @XmlAttribute(name = "name", required = true)
    private String victim;

    public VictimExportXMLDto() {
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }
}
