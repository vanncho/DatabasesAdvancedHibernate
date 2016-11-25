package app.entities.anomalies.dto.imports.xml;

import app.entities.anomalies.dto.exports.xml.AnomalyWithVictimImportXMLDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyXMLImportDto {

    @XmlElement(name = "anomaly")
    private List<AnomalyWithVictimImportXMLDto> anomalies;

    public AnomalyXMLImportDto() {
        this.setAnomalies(new ArrayList<>());
    }

    public List<AnomalyWithVictimImportXMLDto> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<AnomalyWithVictimImportXMLDto> anomalies) {
        this.anomalies = anomalies;
    }
}
