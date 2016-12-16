package app.domains.dtos.input;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class LensesImportJson implements Serializable {

    @Expose
    private String make;

    @Expose
    private int focalLength;

    @Expose
    private double maxAperture;

    @Expose
    private String compatibleWith;

    public LensesImportJson() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }
}
