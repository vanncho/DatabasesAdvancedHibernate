package app.services;

import app.domains.enumerations.Role;
import app.domains.models.Album;
import app.domains.models.AlbumRole;
import app.domains.models.User;

public interface AlbumRoleService {

    void create(AlbumRole albumRole);

    AlbumRole create(User user, Album album, Role role);
}
