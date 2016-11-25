package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserJsonDto implements Serializable {

    @Expose
    private long id;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    public UserJsonDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
