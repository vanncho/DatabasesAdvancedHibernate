package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CategoryJsonDto implements Serializable {

    @Expose
    private long id;

    @Expose
    private String name;

    public CategoryJsonDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
