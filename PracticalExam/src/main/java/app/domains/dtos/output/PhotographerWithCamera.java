package app.domains.dtos.output;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "photographer")
public class PhotographerWithCamera implements Serializable {

    @XmlAttribute(name = "name")
    private String fullName;

    @XmlAttribute(name = "primary-camera")
    private String primaryCameraMake;

    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private Set<String> photographerLensConcat;

    public PhotographerWithCamera() {
        this.setPhotographerLensConcat(new HashSet<>());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrimaryCameraMake() {
        return primaryCameraMake;
    }

    public void setPrimaryCameraMake(String primaryCameraMake) {
        this.primaryCameraMake = primaryCameraMake;
    }

    public Set<String> getPhotographerLensConcat() {
        return photographerLensConcat;
    }

    public void setPhotographerLensConcat(Set<String> photographerLensConcat) {
        this.photographerLensConcat = photographerLensConcat;
    }

    public void addLens(String lens) {
        this.photographerLensConcat.add(lens);
    }
}
