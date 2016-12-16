package app.domains.dtos.output;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationExportXml implements Serializable {

    @XmlAttribute(name = "name")
    private String locationName;

    @XmlElementWrapper(name = "workshops")
    @XmlElement(name = "workshop")
    private List<WorkshopsWithParticipantsExportXml> workshopsWithParticipantsExportXml;

    public LocationExportXml() {
        this.setWorkshopsWithParticipantsExportXml(new ArrayList<>());
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<WorkshopsWithParticipantsExportXml> getWorkshopsWithParticipantsExportXml() {
        return workshopsWithParticipantsExportXml;
    }

    public void setWorkshopsWithParticipantsExportXml(List<WorkshopsWithParticipantsExportXml> workshopsWithParticipantsExportXml) {
        this.workshopsWithParticipantsExportXml = workshopsWithParticipantsExportXml;
    }
}
