package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductUserJsonDtoExport implements Serializable {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductUserJsonDtoExport() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
