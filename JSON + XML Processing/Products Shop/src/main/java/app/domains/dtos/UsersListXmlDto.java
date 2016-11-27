package app.domains.dtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersListXmlDto implements Serializable {

    @XmlElement(name = "user")
    List<UserDto> userDtos;

    public UsersListXmlDto() {
        this.userDtos = new ArrayList<>();
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }
}
