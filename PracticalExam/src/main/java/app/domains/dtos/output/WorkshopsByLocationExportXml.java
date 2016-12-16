package app.domains.dtos.output;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsByLocationExportXml implements Serializable {

    @XmlElement(name = "location")
    private List<LocationExportXml> locations;

    public WorkshopsByLocationExportXml() {
        this.setLocations(new ArrayList<>());
    }

    public List<LocationExportXml> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationExportXml> locations) {
        this.locations = locations;
    }

    public void addLocation(LocationExportXml locationExportXml) {
        this.locations.add(locationExportXml);
    }
}
