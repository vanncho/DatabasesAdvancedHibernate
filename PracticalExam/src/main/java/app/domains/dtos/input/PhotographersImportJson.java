package app.domains.dtos.input;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PhotographersImportJson implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String phone;

    @Expose
    private Long[] lenses;

    public PhotographersImportJson() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long[] getLenses() {
        return lenses;
    }

    public void setLenses(Long[] lenses) {
        this.lenses = lenses;
    }
}
