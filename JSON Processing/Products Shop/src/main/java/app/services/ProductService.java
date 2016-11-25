package app.services;

import app.domains.dtos.ProductJsonDto;
import app.domains.dtos.ProductJsonDtoExport;
import app.domains.dtos.UserJsonDto;

import java.util.List;

public interface ProductService {

    void create(ProductJsonDto product);

    List<ProductJsonDto> allProducts();

    void updateProductsWithBuyerAndSeller(UserJsonDto buyer, UserJsonDto seller, long id);

    List<ProductJsonDtoExport> productsInRange();
}
