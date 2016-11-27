package app.services;

import app.domains.dtos.ProductDto;
import app.domains.dtos.ProductJsonDtoExport;
import app.domains.dtos.UserDto;

import java.util.List;

public interface ProductService {

    void create(ProductDto product);

    List<ProductDto> allProducts();

    void updateProductsWithBuyerAndSeller(UserDto buyer, UserDto seller, long id);

    List<ProductJsonDtoExport> productsInRange();
}
