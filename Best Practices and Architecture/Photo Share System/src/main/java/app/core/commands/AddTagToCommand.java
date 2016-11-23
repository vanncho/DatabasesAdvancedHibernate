package app.core.commands;

import app.domains.models.Album;
import app.domains.models.Tag;
import app.services.AlbumService;
import app.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTagToCommand extends Command {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TagService tagService;

    protected AddTagToCommand(String[] data) {
        super(data);
    }

    /**
     * AddTagTo <albumName> <tag>
     */
    @Override
    public String Execute() {

        String albumName = this.getData()[1];
        String tagName = this.getData()[2];

        Album album = this.albumService.findAlbumByName(albumName);
        Tag tag = this.tagService.findTagByName(tagName);

        album.getTags().add(tag);
        this.albumService.create(album);

        return String.format("Tag %s added to Album %s", tagName, albumName);
    }
}
