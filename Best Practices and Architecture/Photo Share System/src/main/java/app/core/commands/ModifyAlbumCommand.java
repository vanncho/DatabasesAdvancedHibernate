package app.core.commands;

import app.domains.enumerations.Color;
import app.domains.models.Album;
import app.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyAlbumCommand extends Command {

    @Autowired
    private AlbumService albumService;

    protected ModifyAlbumCommand(String[] data) {
        super(data);
    }
    /**
     * ModifyAlbum <albumId> <property> <new value>
     * For example
     * ModifyAlbum Name <new name>
     * ModifyAlbum BackgroundColor <new color>
     * ModifyAlbum IsPublic <True/False>
     */
    @Override
    public String Execute() {

        Integer albumId = Integer.valueOf(this.getData()[1]);
        Album album = this.albumService.findAlbumById(albumId);

        if (album == null) {
            throw new IllegalArgumentException(String.format("No album with id: %d found.", albumId));
        }

        String property = this.getData()[1];
        String propertyValue = this.getData()[2];

        switch (property) {

            case "name": album.setName(propertyValue);
                break;
            case "backgroundColor": album.setBackgroundColor(Color.valueOf(propertyValue));
                break;
            case "isPublic": album.setPublic(Boolean.valueOf(propertyValue));
                break;
            default: throw new IllegalArgumentException("No such property.");
        }

        this.albumService.create(album);
        return String.format("Property %s changed to %s.", property, propertyValue);
    }
}
