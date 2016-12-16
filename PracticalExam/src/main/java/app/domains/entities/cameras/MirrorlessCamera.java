package app.domains.entities.cameras;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MC")
public class MirrorlessCamera extends BasicCamera {

    private static final String TYPE = "Mirrorless";

    public MirrorlessCamera() {
    }

    private MirrorlessCamera(String make, String model, Boolean isFullFrame, Integer minISO, Integer maxISO, Integer maxShutterSpeed, String maxVideoResolution, Integer maxFrameRate) {
        super(TYPE, make, model, isFullFrame, minISO, maxISO, maxShutterSpeed, maxVideoResolution, maxFrameRate);
    }
}
