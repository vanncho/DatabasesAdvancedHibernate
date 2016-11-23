package app.core.commands;

import app.domains.models.Album;
import app.domains.models.Picture;
import app.services.AlbumService;
import app.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadPictureCommand extends Command {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PictureService pictureService;

    protected UploadPictureCommand(String[] data) {
        super(data);
    }
    /**
     * UploadPicture <albumName> <pictureTitle> <pictureFilePath>
     */
    @Override
    public String Execute() {

        String albumName = this.getData()[1];
        String pictureTitle = this.getData()[2];
        String picturePath = this.getData()[3];

        Album album = this.albumService.findAlbumByName(albumName);

        if (album == null) {
            throw new IllegalArgumentException("No album found with name " + albumName);
        }

        Picture picture = this.pictureService.create(pictureTitle, null, picturePath);
        this.pictureService.create(picture);

        album.getPictures().add(picture);
        this.albumService.create(album);

        return "Picture upload successfully.";
    }
}
