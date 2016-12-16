package app.domains.dtos.input;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoriesImportXml implements Serializable {

    @XmlElement(name = "accessory")
    private List<AccessoryXml> accessories;

    public AccessoriesImportXml() {
        this.setAccessories(new ArrayList<>());
    }

    public List<AccessoryXml> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<AccessoryXml> accessories) {
        this.accessories = accessories;
    }
}
