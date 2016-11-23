package app.core.commands;

import app.domains.models.Album;
import app.domains.models.AlbumRole;
import app.domains.models.Tag;
import app.domains.models.User;
import app.services.AlbumService;
import app.services.TagService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAlbumCommand extends Command {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    protected CreateAlbumCommand(String[] data) {
        super(data);
    }

    /**
     *CreateAlbum <username> <albumTitle> <BackgroundColor> <tag1> <tag2>...<tagN>
     */
    @Override
    public String Execute() {

        String userName = this.getData()[1];
        String albumTitle = this.getData()[2];
        String backgroundColor = this.getData()[3];

        User user = this.userService.findUserByUsername(userName);
        Album album = this.albumService.create(albumTitle, backgroundColor);
        AlbumRole albumRole = new AlbumRole();
        albumRole.setUser(user);
        album.getAlbumRoles().add(albumRole);

        for (int i = 4; i < this.getData().length; i++) {

            Tag tag = this.tagService.findTagByName(this.getData()[i]);
            album.getTags().add(tag);
        }

        this.albumService.create(album);
        return "The album has been created.";
    }
}
