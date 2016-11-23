package app.core.commands;

import app.domains.enumerations.Role;
import app.domains.models.Album;
import app.domains.models.AlbumRole;
import app.domains.models.User;
import app.services.AlbumRoleService;
import app.services.AlbumService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShareAlbumCommand extends Command {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRoleService albumRoleService;

    @Autowired
    private UserService userService;

    protected ShareAlbumCommand(String[] data) {
        super(data);
    }
    /**
     * ShareAlbum <albumId> <username> <permission>
     * For example:
     * ShareAlbum 4 dragon321 Owner
     * ShareAlbum 4 dragon11 Viewer
     */
    @Override
    public String Execute() {

        Integer albumId = Integer.valueOf(this.getData()[1]);
        String username = this.getData()[2];
        Role role = Role.valueOf(this.getData()[3].toUpperCase());

        Album album = this.albumService.findAlbumById(albumId);

        if (album == null) {
            throw new IllegalArgumentException(String.format("No album found with id: %d", albumId));
        }

        User user = this.userService.findUserById(albumId);

        if (user == null) {
            throw new IllegalArgumentException(String.format("No user found with username: %s", username));
        }

        AlbumRole albumRole = this.albumRoleService.create(user, album, role);
        this.albumRoleService.create(albumRole);

        album.getAlbumRoles().add(albumRole);
        this.albumService.create(album);

        user.getAlbumRoles().add(albumRole);
        this.userService.create(user);

        return String.format("Album with %d shared to user: %s with role: %s", albumId, username, this.getData()[3].toUpperCase());
    }
}
