package app.repositories;

import app.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    @Query(value = "SELECT u FROM User AS u INNER JOIN u.productsBought AS p WHERE u.productsBought.size >= 1")
    List<User> usersWithSoldProducts();

    @Query(value = "SELECT u FROM User AS u INNER JOIN u.productsSold AS ps GROUP BY u HAVING COUNT(u.productsSold.size) >= 1 ORDER BY COUNT(u.productsSold.size) DESC, u.lastName ASC")
    List<User> usersAndProducts();
}
