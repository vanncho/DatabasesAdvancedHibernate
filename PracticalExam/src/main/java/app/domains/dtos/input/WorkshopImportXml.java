package app.domains.dtos.input;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopImportXml implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @XmlAttribute(name = "location")
    private String location;

    @XmlAttribute(name = "price")
    private Double pricePerParticipant;

    @XmlElement(name = "trainer")
    private String fullName;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<WorkshopParticipantsImportXml> participant;

    public WorkshopImportXml() {
        this.setParticipant(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(Double pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<WorkshopParticipantsImportXml> getParticipant() {
        return participant;
    }

    public void setParticipant(List<WorkshopParticipantsImportXml> participant) {
        this.participant = participant;
    }
}
