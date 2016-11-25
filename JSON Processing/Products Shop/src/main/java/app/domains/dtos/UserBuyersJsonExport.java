package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class UserBuyersJsonExport implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Set<ProductJsonDtoExport> soldProducts;

    public UserBuyersJsonExport() {
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

    public Set<ProductJsonDtoExport> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductJsonDtoExport> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
