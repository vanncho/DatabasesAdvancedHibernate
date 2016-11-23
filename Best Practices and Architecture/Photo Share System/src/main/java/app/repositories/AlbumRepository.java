package app.repositories;

import app.domains.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    Album findByName(String name);

    Album findById(Integer id);
}
