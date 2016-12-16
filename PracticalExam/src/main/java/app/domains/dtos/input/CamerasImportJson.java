package app.domains.dtos.input;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CamerasImportJson implements Serializable {

    @Expose
    private String type;

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Boolean isFullFrame;

    @Expose
    private Integer minISO;

    @Expose
    private Integer maxISO;

    @Expose
    private Integer maxShutterSpeed;

    @Expose
    private String maxVideoResolution;

    @Expose
    private Integer maxFrameRate;

    public CamerasImportJson() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getIsFullFrame() {
        return isFullFrame;
    }

    public void setIsFullFrame(Boolean isFullFrame) {
        this.isFullFrame = isFullFrame;
    }

    public Integer getMinISO() {
        return minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
