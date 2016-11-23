package app.factories;

import app.domains.enumerations.Role;
import app.domains.models.Album;
import app.domains.models.AlbumRole;
import app.domains.models.User;

public class AlbumRoleFactory {

    public AlbumRole create(Album album, String role, User user) {
        AlbumRole albumRole = new AlbumRole();
        albumRole.setAlbum(album);
        albumRole.setRole(Role.valueOf(role));
        albumRole.setUser(user);
        return albumRole;
    }
}
