package app.domains.dtos.output;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "participants")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ParticipantExportXml {

    @XmlAttribute
    private long count;

    @XmlElement(name = "participant")
    private List<String> fullName;

    public ParticipantExportXml() {
        this.setFullName(new ArrayList<>());
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<String> getFullName() {
        return fullName;
    }

    public void setFullName(List<String> fullName) {
        this.fullName = fullName;
    }
}
