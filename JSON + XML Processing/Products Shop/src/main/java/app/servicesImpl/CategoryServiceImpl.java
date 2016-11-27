package app.servicesImpl;

import app.domains.dtos.CategoryDto;
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
    public void create(CategoryDto categoryDto) {
        Category category = this.convertDtoToEntity(categoryDto);
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<CategoryDto> allCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {

            CategoryDto currCat= new CategoryDto();
            currCat.setId(category.getId());
            currCat.setName(category.getName());
            categoryDtos.add(currCat);
        }
        return categoryDtos;
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

    private CategoryDto convertEntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private Category convertDtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }
}
