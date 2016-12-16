package app.repositories;

import app.domains.entities.photographers.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {

    @Query(value = "SELECT p FROM Photographer AS p ORDER BY p.firstName, p.lastName DESC ")
    List<Photographer> getAllPhotographers();

    @Query(value = "SELECT DISTINCT p FROM Photographer AS p INNER JOIN p.lenses AS l WHERE p.primaryCamera.type = 'DSLR' ORDER BY p.firstName")
    List<Photographer> getAllLandscapePhotographers();

    @Query(value = "SELECT p FROM Photographer AS p WHERE p.primaryCamera.make = p.secondaryCamera.make AND p.lenses.size > 0")
    List<Photographer> getPhotographersWithSameCameraMake();

    Photographer findOneByFirstNameAndLastName(String firstName, String lastName);
}
