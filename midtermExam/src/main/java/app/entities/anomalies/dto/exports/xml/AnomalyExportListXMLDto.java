package app.entities.anomalies.dto.exports.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyExportListXMLDto {

    @XmlElement(name = "anomaly")
    private List<AnomalyExportXMLDto> anomalyExportXMLDtoList;

    public AnomalyExportListXMLDto() {
        this.setAnomalyExportXMLDtoList(new ArrayList<>());
    }

    public List<AnomalyExportXMLDto> getAnomalyExportXMLDtoList() {
        return anomalyExportXMLDtoList;
    }

    public void setAnomalyExportXMLDtoList(List<AnomalyExportXMLDto> anomalyExportXMLDtoList) {
        this.anomalyExportXMLDtoList = anomalyExportXMLDtoList;
    }
}
