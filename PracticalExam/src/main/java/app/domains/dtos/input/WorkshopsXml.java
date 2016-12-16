package app.domains.dtos.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsXml implements Serializable {

    @XmlElement(name = "workshop")
    private List<WorkshopImportXml> workshops;

    public WorkshopsXml() {
        this.setWorkshops(new ArrayList<>());
    }

    public List<WorkshopImportXml> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopImportXml> workshops) {
        this.workshops = workshops;
    }
}
