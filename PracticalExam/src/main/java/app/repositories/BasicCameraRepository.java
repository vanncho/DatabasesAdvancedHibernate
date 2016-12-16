package app.repositories;

import app.domains.entities.cameras.BasicCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicCameraRepository extends JpaRepository<BasicCamera, Long> {
}
