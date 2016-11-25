package app.servicesImpl;

import app.domains.dtos.CategoryJsonDto;
import app.domains.dtos.CategoryProductsJsonDtoExport;
import app.domains.entities.Category;
import app.repositories.CategoryRepository;
import app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(CategoryJsonDto categoryJsonDto) {
        Category category = this.convertDtoToEntity(categoryJsonDto);
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<CategoryJsonDto> allCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryJsonDto> categoryJsonDtos = new ArrayList<>();

        for (Category category : categories) {

            CategoryJsonDto currCat= new CategoryJsonDto();
            currCat.setId(category.getId());
            currCat.setName(category.getName());
            categoryJsonDtos.add(currCat);
        }
        return categoryJsonDtos;
    }

    @Override
    public void updateCategoriesProducts(long catId, long prodId) {
        this.categoryRepository.updateCategoriesProducts(catId, prodId);
    }

    @Override
    public List<CategoryProductsJsonDtoExport> categoriesByProductsCount() {

        List<Object[]> objects = this.categoryRepository.categoriesByProductsCount();
        List<CategoryProductsJsonDtoExport> objectsDtos = new ArrayList<>();

        for (Object[] object : objects) {

            String name = (String) object[0];
            long count = (long) object[1];
            double avPr = (double) object[2];
            BigDecimal totRev = (BigDecimal) object[3];

            CategoryProductsJsonDtoExport odts = new CategoryProductsJsonDtoExport();

            odts.setCategory(name);
            odts.setProductsCount(count);
            odts.setAveragePrice(avPr);
            odts.setTotalRevenue(totRev);
            objectsDtos.add(odts);
            }

        return objectsDtos;
    }

    private CategoryJsonDto convertEntityToDto(Category category) {
        CategoryJsonDto categoryJsonDto = new CategoryJsonDto();
        categoryJsonDto.setId(category.getId());
        categoryJsonDto.setName(category.getName());
        return categoryJsonDto;
    }

    private Category convertDtoToEntity(CategoryJsonDto categoryJsonDto) {
        Category category = new Category();
        category.setId(categoryJsonDto.getId());
        category.setName(categoryJsonDto.getName());
        return category;
    }
}
