package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductJsonDto implements Serializable {

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private UserJsonDto buyer;

    @Expose
    private UserJsonDto seller;

    public ProductJsonDto() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserJsonDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserJsonDto buyer) {
        this.buyer = buyer;
    }

    public UserJsonDto getSeller() {
        return seller;
    }

    public void setSeller(UserJsonDto seller) {
        this.seller = seller;
    }
}
