package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserProductsJsonDtoExport implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private List<ProductUserCountJsonDtoExport> soldProducts;

    public UserProductsJsonDtoExport() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ProductUserCountJsonDtoExport> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductUserCountJsonDtoExport> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
