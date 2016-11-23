package app.servicesImpl;

import app.domains.enumerations.Role;
import app.domains.models.Album;
import app.domains.models.AlbumRole;
import app.domains.models.User;
import app.repositories.AlbumRoleRepository;
import app.services.AlbumRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumRoleServiceImpl implements AlbumRoleService {

    private final AlbumRoleRepository albumRoleRepository;

    @Autowired
    public AlbumRoleServiceImpl(AlbumRoleRepository albumRoleRepository) {
        this.albumRoleRepository = albumRoleRepository;
    }

    @Override
    public void create(AlbumRole albumRole) {
        this.albumRoleRepository.save(albumRole);
    }

    @Override
    public AlbumRole create(User user, Album album, Role role) {
        AlbumRole albumRole = new AlbumRole();
        albumRole.setUser(user);
        albumRole.setAlbum(album);
        albumRole.setRole(role);
        this.create(albumRole);
        return albumRole;
    }
}
