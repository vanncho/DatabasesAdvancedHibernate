package app.domains.entities.cameras;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DC")
public class DSLRCamera extends BasicCamera {

    private static final String TYPE = "DSLR";

    public DSLRCamera() {
    }

    public DSLRCamera(String make, String model, Boolean isFullFrame, Integer minISO, Integer maxISO, Integer maxShutterSpeed, String maxVideoResolution, Integer maxFrameRate) {
        super(TYPE, make, model, isFullFrame, minISO, maxISO, maxShutterSpeed, maxVideoResolution, maxFrameRate);
    }
}
