package app.services;

import app.domains.dtos.CategoryDto;
import app.domains.dtos.CategoryProductsJsonDtoExport;

import java.util.List;

public interface CategoryService {

    void create(CategoryDto category);

    List<CategoryDto> allCategories();

    void updateCategoriesProducts(long catId, long prodId);

    List<CategoryProductsJsonDtoExport> categoriesByProductsCount();
}
