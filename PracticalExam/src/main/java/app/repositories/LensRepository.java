package app.repositories;

import app.domains.entities.lens.Lens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LensRepository extends JpaRepository<Lens, Long> {
}
