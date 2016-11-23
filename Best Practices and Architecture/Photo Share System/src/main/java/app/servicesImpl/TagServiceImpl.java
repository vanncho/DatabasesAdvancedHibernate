package app.servicesImpl;

import app.domains.models.Tag;
import app.factories.TagFactory;
import app.repositories.TagRepository;
import app.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagFactory tagFactory;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagFactory tagFactory) {
        this.tagRepository = tagRepository;
        this.tagFactory = tagFactory;
    }

    @Override
    public void create(Tag tag) {
        this.tagRepository.save(tag);
    }

    @Override
    public Tag create(String name) {
        Tag tag = this.tagFactory.create(name);
        tag.setName(name);
        this.create(tag);
        return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        return this.tagRepository.findByName(name);
    }
}
