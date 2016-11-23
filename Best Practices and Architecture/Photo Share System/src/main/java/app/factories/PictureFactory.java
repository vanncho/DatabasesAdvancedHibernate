package app.factories;

import app.domains.models.Picture;

public class PictureFactory {

    public Picture create(String title, String caption, String path) {
        Picture picture = new Picture();
        picture.setTitle(title);
        picture.setCaption(caption);
        picture.setPath(path);
        return picture;
    }
}
