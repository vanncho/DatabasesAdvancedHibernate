package app.repositories;

import app.domains.dtos.ProductJsonDto;
import app.domains.entities.Product;
import app.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product AS p SET p.buyer = :buyer, p.seller = :seller WHERE p.id = :id")
    void updateProductsWithBuyerAndSeller(@Param(value = "buyer") User buyer, @Param(value = "seller") User seller, @Param(value = "id") long id);

    @Query(value = "SELECT p FROM Product AS p WHERE p.price >= 500 AND p.price <= 1000 AND p.buyer IS NULL ORDER BY p.price ASC")
    List<Product> productsInRange();
}
