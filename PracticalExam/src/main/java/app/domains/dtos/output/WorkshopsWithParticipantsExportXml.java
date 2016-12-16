package app.domains.dtos.output;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsWithParticipantsExportXml {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "total-profit")
    private Double totalProfit;

    @XmlElement(name = "participants")
    private ParticipantExportXml photographers;

    public WorkshopsWithParticipantsExportXml() {
            }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ParticipantExportXml getPhotographers() {
        return photographers;
    }

    public void setPhotographers(ParticipantExportXml photographers) {
        this.photographers = photographers;
    }
}
