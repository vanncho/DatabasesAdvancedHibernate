package app.commander;

import app.domains.dtos.*;
import app.parsers.json.JsonParser;
import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class TerminalCommander implements CommandLineRunner {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final UserService userService;

    private final JsonParser jsonParser;

    @Autowired
    public TerminalCommander(CategoryService categoryService,
                             ProductService productService,
                             UserService userService,
                             JsonParser jsonParser) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.seedData();
    }

    private void seedData() {

        this.importUsersJson();

        this.importProductsJson();

        this.updateProductsWithBuyersAndSellers();

        this.importCategoriesJson();

        this.updateCategoriesProducts();

        this.productsInRangeWriteToJson();

        this.usersWithProductsBought();

        this.categoriesByProductsCount();
        
        this.usersAndProducts();
    }

    private void usersAndProducts() {
        List<UsersCountJsonDtoExport> upjd = this.userService.usersAndProducts();
        try {
            this.jsonParser.exportJson(upjd, "src/main/resources/files/exports/json/users-and-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void categoriesByProductsCount() {

        List<CategoryProductsJsonDtoExport> categoriesProducts = this.categoryService.categoriesByProductsCount();
        try {
            this.jsonParser.exportJson(categoriesProducts, "src/main/resources/files/exports/json/categories-by-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void usersWithProductsBought() {

        List<UserBuyersJsonExport> usersWithBuyers = this.userService.usersWithSoldProducts();
        try {
            this.jsonParser.exportJson(usersWithBuyers, "src/main/resources/files/exports/json/users-sold-products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void productsInRangeWriteToJson() {

        List<ProductJsonDtoExport> productsRange = this.productService.productsInRange();
        try {
            this.jsonParser.exportJson(productsRange, "src/main/resources/files/exports/json/products-range.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCategoriesProducts() {

        List<CategoryJsonDto> categoryJsonDtos = this.categoryService.allCategories();
        List<ProductJsonDto> productJsonDtos = this.productService.allProducts();

        for (int i = 0; i < productJsonDtos.size(); i++) {

            Random random = new Random();
            ProductJsonDto productJsonDto = productJsonDtos.get(i);

            int catId = random.nextInt(categoryJsonDtos.size());
            while (catId == 0) {
                catId = random.nextInt(categoryJsonDtos.size());
            }

            CategoryJsonDto categoryJsonDto = categoryJsonDtos.get(catId);
            this.categoryService.updateCategoriesProducts(categoryJsonDto.getId(), productJsonDto.getId());
        }
    }

    private void importCategoriesJson() {

        try {
            CategoryJsonDto[] categoryJsonDtos = null;
            categoryJsonDtos = this.jsonParser.importJson(CategoryJsonDto[].class, "/files/imports/json/categories.json");

            for (CategoryJsonDto categoryJsonDto : categoryJsonDtos) {
                this.categoryService.create(categoryJsonDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProductsWithBuyersAndSellers() {

        List<UserJsonDto> allUsers = this.userService.getAllUsers();
        List<ProductJsonDto> productJsonDtos = this.productService.allProducts();

        for (ProductJsonDto productJsonDto : productJsonDtos) {

            long productId = productJsonDto.getId();
            Random random = new Random();
            int buyerId = random.nextInt(allUsers.size());
            int sellerId = random.nextInt(allUsers.size());

            while (buyerId == 0 || sellerId == 0) {
                buyerId = random.nextInt(allUsers.size());
                sellerId = random.nextInt(allUsers.size());
            }

            UserJsonDto buyer = this.userService.findUserById(buyerId);
            UserJsonDto seller = this.userService.findUserById(sellerId);

            this.productService.updateProductsWithBuyerAndSeller(buyer, seller, productId);
        }
    }

    private void importProductsJson() {
        try {
            ProductJsonDto[] productsJsonDto = null;
            productsJsonDto = this.jsonParser.importJson(ProductJsonDto[].class, "/files/imports/json/products.json");

            for (ProductJsonDto productJsonDto : productsJsonDto) {

                this.productService.create(productJsonDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importUsersJson() {
        try {
            UserJsonDto[] usersJsonDto = null;
            usersJsonDto = this.jsonParser.importJson(UserJsonDto[].class, "/files/imports/json/users.json");

            for (UserJsonDto userJsonDto : usersJsonDto) {
                this.userService.create(userJsonDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
