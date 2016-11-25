package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ProductUserCountJsonDtoExport {

    @Expose
    private int count;

    @Expose
    List<ProductUserJsonDtoExport> products;

    public ProductUserCountJsonDtoExport() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductUserJsonDtoExport> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUserJsonDtoExport> products) {
        this.products = products;
    }
}
