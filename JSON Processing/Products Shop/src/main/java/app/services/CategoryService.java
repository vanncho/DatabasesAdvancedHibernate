package app.services;

import app.domains.dtos.CategoryJsonDto;
import app.domains.dtos.CategoryProductsJsonDtoExport;

import java.util.List;

public interface CategoryService {

    void create(CategoryJsonDto category);

    List<CategoryJsonDto> allCategories();

    void updateCategoriesProducts(long catId, long prodId);

    List<CategoryProductsJsonDtoExport> categoriesByProductsCount();
}
