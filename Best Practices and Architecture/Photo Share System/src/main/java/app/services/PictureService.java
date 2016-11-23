package app.services;

import app.domains.models.Picture;

public interface PictureService {

    void create(Picture picture);

    Picture create(String title, String caption, String path);

    Picture findPictureByPath(String path);
}
