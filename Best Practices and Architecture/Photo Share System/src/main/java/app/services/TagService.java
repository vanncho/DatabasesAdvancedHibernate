package app.services;

import app.domains.models.Tag;

public interface TagService {

    void create(Tag tag);

    Tag create(String name);

    Tag findTagByName(String name);
}
