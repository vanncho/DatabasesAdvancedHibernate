package app.repositories;

import app.domains.entities.workshops.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

    @Query(value = "SELECT w FROM Workshop AS w WHERE w.participants.size >= 5")
    List<Workshop> getWorkshopsByLocation();
}
