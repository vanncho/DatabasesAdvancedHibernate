package app.services;

import app.domains.models.Album;

public interface AlbumService {

    void create(Album album);

    Album create(String name, String color);

    Album findAlbumByName(String name);

    Album findAlbumById(Integer id);
}
