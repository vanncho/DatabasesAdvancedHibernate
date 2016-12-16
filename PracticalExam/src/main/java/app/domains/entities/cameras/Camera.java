package app.domains.entities.cameras;

import java.io.Serializable;

public interface Camera extends Serializable {

    long getId();

    void setId(long id);

    String getType();

    void setType(String type);

    String getMake();

    void setMake(String make);

    String getModel();

    void setModel(String model);

    Boolean getIsFullFrame();

    void setIsFullFrame(Boolean isfullFrame);

    Integer getMinISO();

    void setMinISO(Integer minISO);

    Integer getMaxISO();

    void setMaxISO(Integer maxISO);

    Integer getMaxShutterSpeed();

    void setMaxShutterSpeed(Integer maxShutterSpeed);

    String getMaxVideoResolution();

    void setMaxVideoResolution(String maxVideoResolution);

    Integer getMaxFrameRate();

    void setMaxFrameRate(Integer maxFrameRate);
}
