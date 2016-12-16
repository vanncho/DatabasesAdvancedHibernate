package app.domains.dtos.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LandscapePhotographersExportJson implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    @SerializedName(value = "cameraMake")
    private String dslrCameraMake;

    @Expose
    private int lensesCount;

    public LandscapePhotographersExportJson() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDslrCameraMake() {
        return dslrCameraMake;
    }

    public void setDslrCameraMake(String dslrCameraMake) {
        this.dslrCameraMake = dslrCameraMake;
    }

    public int getLensesCount() {
        return lensesCount;
    }

    public void setLensesCount(int lensesCount) {
        this.lensesCount = lensesCount;
    }
}
