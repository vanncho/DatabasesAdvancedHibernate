package app.domains.entities.cameras;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "camera_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicCamera implements Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "make", nullable = false)
    private String make;

    @NotNull
    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "is_full_frame")
    private Boolean isFullFrame;

    @NotNull
    @Column(name = "min_iso", nullable = false)
    @Min(100)
    private Integer minISO;

    @Column(name = "max_iso")
    private Integer maxISO;

    @Column(name = "max_shutter_speed")
    private Integer maxShutterSpeed;

    @Column(name = "max_video_resolution")
    private String maxVideoResolution;

    @Column(name = "max_frame_rate")
    private Integer maxFrameRate;

    protected BasicCamera() {
    }

    protected BasicCamera(String type, String make, String model, Boolean isFullFrame, Integer minISO, Integer maxISO, Integer maxShutterSpeed, String maxVideoResolution, Integer maxFrameRate) {
        this();
        this.type = type;
        this.make = make;
        this.model = model;
        this.isFullFrame = isFullFrame;
        this.minISO = minISO;
        this.maxISO = maxISO;
        this.maxShutterSpeed = maxShutterSpeed;
        this.maxVideoResolution = maxVideoResolution;
        this.maxFrameRate = maxFrameRate;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Boolean getIsFullFrame() {
        return isFullFrame;
    }

    @Override
    public void setIsFullFrame(Boolean isfullFrame) {
        isFullFrame = isfullFrame;
    }

    @Override
    public Integer getMinISO() {
        return minISO;
    }

    @Override
    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    @Override
    public Integer getMaxISO() {
        return maxISO;
    }

    @Override
    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }

    @Override
    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    @Override
    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    @Override
    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    @Override
    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    @Override
    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    @Override
    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
