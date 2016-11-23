package app.servicesImpl;

import app.domains.models.Album;
import app.factories.AlbumFactory;
import app.repositories.AlbumRepository;
import app.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final AlbumFactory albumFactory;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            AlbumFactory albumFactory) {
        this.albumRepository = albumRepository;
        this.albumFactory = albumFactory;
    }

    @Override
    public void create(Album album) {
        this.albumRepository.save(album);
    }

    @Override
    public Album create(String name, String color) {
        Album album = this.albumFactory.create(name, color);
        this.create(album);
        return album;
    }

    @Override
    public Album findAlbumByName(String name) {
        return this.albumRepository.findByName(name);
    }

    @Override
    public Album findAlbumById(Integer id) {
        return this.albumRepository.findById(id);
    }
}
