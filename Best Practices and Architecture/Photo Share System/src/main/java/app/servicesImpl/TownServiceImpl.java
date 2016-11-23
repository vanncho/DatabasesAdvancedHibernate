package app.servicesImpl;

import app.domains.models.Town;
import app.factories.TownFactory;
import app.repositories.TownRepository;
import app.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final TownFactory townFactory;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, TownFactory townFactory) {
        this.townRepository = townRepository;
        this.townFactory = townFactory;
    }

    @Override
    public void create(Town town) {
        this.townRepository.save(town);
    }

    @Override
    public Town create(String name, String country) {
        Town town = this.townFactory.create(name, country);
        this.create(town);
        return town;
    }

    @Override
    public Town findTownByName(String name) {
        return this.townRepository.findByName(name);
    }
}
