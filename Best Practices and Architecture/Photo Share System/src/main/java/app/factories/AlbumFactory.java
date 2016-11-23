package app.factories;

import app.domains.enumerations.Color;
import app.domains.models.Album;

public class AlbumFactory {

    public Album create(String name, String color) {
        Album album = new Album();
        album.setName(name);
        album.setBackgroundColor(Color.valueOf(color));
        return album;
    }
}
