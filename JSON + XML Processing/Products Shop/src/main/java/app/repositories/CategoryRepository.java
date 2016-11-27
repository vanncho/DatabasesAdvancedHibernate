package app.repositories;

import app.domains.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categories_products(category_id, product_id) VALUES(:catId, :prodId)", nativeQuery = true)
    void updateCategoriesProducts(@Param(value = "catId") long catId, @Param(value = "prodId") long prodId);

    @Query(value = "SELECT c.name, COUNT(cp.id), AVG(cp.price), SUM(cp.price) FROM Category AS c " +
                    "INNER JOIN c.products AS cp GROUP BY c")
    List<Object[]> categoriesByProductsCount();
}
