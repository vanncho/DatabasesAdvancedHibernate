package app.domains.dtos.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersWithCameraExportXml implements Serializable {

    @XmlElement(name = "photographer")
    private List<PhotographerWithCamera> photographers;

    public PhotographersWithCameraExportXml() {
        this.photographers = new ArrayList<>();
    }

    public List<PhotographerWithCamera> getPhotographers() {
        return photographers;
    }

    public void setPhotographers(List<PhotographerWithCamera> photographers) {
        this.photographers = photographers;
    }

    public void addPhotographer(PhotographerWithCamera photographerWithCamera) {
        this.photographers.add(photographerWithCamera);
    }
}
