package app.servicesImpl;

import app.entities.stars.Star;
import app.repositories.StarRepository;
import app.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public void create(Star star) {
        this.starRepository.saveAndFlush(star);
    }

    @Override
    public Star findStarByName(String name) {
        return this.starRepository.findByName(name);
    }
}
