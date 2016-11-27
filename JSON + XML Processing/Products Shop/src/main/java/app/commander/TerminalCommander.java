package app.commander;

import app.domains.dtos.*;
import app.parsers.json.JsonParser;
import app.parsers.xml.XmlParser;
import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class TerminalCommander implements CommandLineRunner {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final UserService userService;

    private final JsonParser jsonParser;
    
    private final XmlParser xmlParser;

    @Autowired
    public TerminalCommander(CategoryService categoryService,
                             ProductService productService,
                             UserService userService,
                             JsonParser jsonParser, 
                             XmlParser xmlParser) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.seedData();
    }

    private void seedData() {

        // JSON
        this.importUsersJson();

        this.importProductsJson();

        this.updateProductsWithBuyersAndSellers();

        this.importCategoriesJson();

        this.updateCategoriesProducts();

        this.productsInRangeWriteToJson();

        this.usersWithProductsBought();

        this.categoriesByProductsCount();

        this.usersAndProducts();
        
        // XML
        //this.importUsersXml(); // duplicate with upper json method, just for test

        //this.importProductsXml(); // duplicate with upper json method, just for test

        //this.importCategoriesXml(); // duplicate with upper json method, just for test

        this.extractAllUsers();

        this.exportAllCategoriesToXml();
    }

    private void exportAllCategoriesToXml() {

        List<CategoryDto> categoryDtos = this.categoryService.allCategories();
        CategoriesListXmlDto categoriesListXmlDto = new CategoriesListXmlDto();
        categoriesListXmlDto.setCategoryDtos(categoryDtos);

        try {
            this.xmlParser.write(categoriesListXmlDto, "src/main/resources/files/exports/xml/categories.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractAllUsers() {

        List<UserDto> userDtos = this.userService.getAllUsers();
        UsersListXmlDto usersListXmlDto = new UsersListXmlDto();
        usersListXmlDto.setUserDtos(userDtos);

        try {
            this.xmlParser.write(usersListXmlDto, "src/main/resources/files/exports/xml/users.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void importCategoriesXml() {

        try {
            CategoriesListXmlDto categoriesListXmlDto = this.xmlParser.read(CategoriesListXmlDto.class, "/files/imports/xml/categories.xml");
            List<CategoryDto> categoryDtos = categoriesListXmlDto.getCategoryDtos();

            for (CategoryDto categoryDto : categoryDtos) {
                this.categoryService.create(categoryDto);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importProductsXml() {

        try {
            ProductsListXmlDto productsListXmlDto = this.xmlParser.read(ProductsListXmlDto.class, "/files/imports/xml/products.xml");
            List<ProductDto> productJsonDto = productsListXmlDto.getProductDtos();

            for (ProductDto productDto : productJsonDto) {
                this.productService.create(productDto);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importUsersXml() {

        try {
            UsersListXmlDto usersListXmlDto = this.xmlParser.read(UsersListXmlDto.class, "/files/imports/xml/users.xml");
            List<UserDto> u = usersListXmlDto.getUserDtos();

            for (UserDto userDto : u) {

                this.userService.create(userDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void usersAndProducts() {
        List<UsersCountDtoExport> upjd = this.userService.usersAndProducts();
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

        List<CategoryDto> categoryDtos = this.categoryService.allCategories();
        List<ProductDto> productDtos = this.productService.allProducts();

        for (int i = 0; i < productDtos.size(); i++) {

            Random random = new Random();
            ProductDto productDto = productDtos.get(i);

            int catId = random.nextInt(categoryDtos.size());
            while (catId == 0) {
                catId = random.nextInt(categoryDtos.size());
            }

            CategoryDto categoryDto = categoryDtos.get(catId);
            this.categoryService.updateCategoriesProducts(categoryDto.getId(), productDto.getId());
        }
    }

    private void importCategoriesJson() {

        try {
            CategoryDto[] categoryDtos = null;
            categoryDtos = this.jsonParser.importJson(CategoryDto[].class, "/files/imports/json/categories.json");

            for (CategoryDto categoryDto : categoryDtos) {
                this.categoryService.create(categoryDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProductsWithBuyersAndSellers() {

        List<UserDto> allUsers = this.userService.getAllUsers();
        List<ProductDto> productDtos = this.productService.allProducts();

        for (ProductDto productDto : productDtos) {

            long productId = productDto.getId();
            Random random = new Random();
            int buyerId = random.nextInt(allUsers.size());
            int sellerId = random.nextInt(allUsers.size());

            while (buyerId == 0 || sellerId == 0) {
                buyerId = random.nextInt(allUsers.size());
                sellerId = random.nextInt(allUsers.size());
            }

            UserDto buyer = this.userService.findUserById(buyerId);
            UserDto seller = this.userService.findUserById(sellerId);

            this.productService.updateProductsWithBuyerAndSeller(buyer, seller, productId);
        }
    }

    private void importProductsJson() {
        try {
            ProductDto[] productsJsonDto = null;
            productsJsonDto = this.jsonParser.importJson(ProductDto[].class, "/files/imports/json/products.json");

            for (ProductDto productDto : productsJsonDto) {

                this.productService.create(productDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importUsersJson() {
        try {
            UserDto[] usersJsonDto = null;
            usersJsonDto = this.jsonParser.importJson(UserDto[].class, "/files/imports/json/users.json");

            for (UserDto userDto : usersJsonDto) {
                this.userService.create(userDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
