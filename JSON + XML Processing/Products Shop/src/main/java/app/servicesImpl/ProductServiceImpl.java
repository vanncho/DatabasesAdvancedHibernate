package app.servicesImpl;

import app.domains.dtos.ProductDto;
import app.domains.dtos.ProductJsonDtoExport;
import app.domains.dtos.UserDto;
import app.domains.entities.Product;
import app.domains.entities.User;
import app.repositories.ProductRepository;
import app.repositories.UserRepository;
import app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(ProductDto productDto) {
        Product product = this.convertDtoToEntity(productDto);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductDto> allProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            ProductDto currProd = this.convertEntityToDto(product);
            productDtos.add(currProd);
        }
        return productDtos;
    }

    @Override
    public void updateProductsWithBuyerAndSeller(UserDto buyer, UserDto seller, long id) {
        User ub = this.userRepository.findById(buyer.getId());
        User us = this.userRepository.findById(seller.getId());

        Random random = new Random();
        int n = random.nextInt(10);

        if (n == 2) {
            ub = null;
        }

        this.productRepository.updateProductsWithBuyerAndSeller(ub, us, id);
    }

    @Override
    public List<ProductJsonDtoExport> productsInRange() {
        List<Product> products = this.productRepository.productsInRange();
        List<ProductJsonDtoExport> productJsonDtos = new ArrayList<>();

        for (Product product : products) {

            ProductJsonDtoExport currProd = new ProductJsonDtoExport();
            currProd.setName(product.getName());
            currProd.setPrice(product.getPrice());
            currProd.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());

            productJsonDtos.add(currProd);
        }
        return productJsonDtos;
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    private Product convertDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return product;
    }
}
