package app.servicesImpl;

import app.domains.dtos.ProductJsonDto;
import app.domains.dtos.ProductJsonDtoExport;
import app.domains.dtos.UserJsonDto;
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
    public void create(ProductJsonDto productJsonDto) {
        Product product = this.convertDtoToEntity(productJsonDto);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductJsonDto> allProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductJsonDto> productJsonDtos = new ArrayList<>();

        for (Product product : products) {
            ProductJsonDto currProd = this.convertEntityToDto(product);
            productJsonDtos.add(currProd);
        }
        return productJsonDtos;
    }

    @Override
    public void updateProductsWithBuyerAndSeller(UserJsonDto buyer, UserJsonDto seller, long id) {
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

    private ProductJsonDto convertEntityToDto(Product product) {
        ProductJsonDto productJsonDto = new ProductJsonDto();
        productJsonDto.setId(product.getId());
        productJsonDto.setName(product.getName());
        productJsonDto.setPrice(product.getPrice());

        return productJsonDto;
    }

    private Product convertDtoToEntity(ProductJsonDto productJsonDto) {
        Product product = new Product();
        product.setId(productJsonDto.getId());
        product.setName(productJsonDto.getName());
        product.setPrice(productJsonDto.getPrice());

        return product;
    }
}
