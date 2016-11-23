package app.repositories;

import app.annotations.Insert;
import app.domains.models.AlbumRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Insert
public interface AlbumRoleRepository extends CrudRepository<AlbumRole, Integer> {

}
