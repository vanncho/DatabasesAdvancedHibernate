package app.core.commands;

import app.domains.models.Tag;
import app.services.TagService;
import app.utilities.TagUtilities;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTagCommand extends Command {

    @Autowired
    private TagService tagService;

    protected AddTagCommand(String[] data) {
        super(data);
    }

    @Override
    public String Execute() {

        String tagName = TagUtilities.validateOrTransform(this.getData()[1]);
        Tag tag = tagService.create(tagName);
        tag.setName(tagName);
        tagService.create(tag);
        return tagName + " was added successfully to database";
    }
}
