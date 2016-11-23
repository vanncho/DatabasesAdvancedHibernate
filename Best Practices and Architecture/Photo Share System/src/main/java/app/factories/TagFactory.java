package app.factories;

import app.domains.models.Tag;

public class TagFactory {

    public Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
