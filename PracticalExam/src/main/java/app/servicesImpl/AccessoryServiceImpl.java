package app.servicesImpl;

import app.domains.dtos.input.AccessoryXml;
import app.domains.entities.accessories.Accessory;
import app.domains.entities.photographers.Photographer;
import app.parsers.mapper.ModelParser;
import app.repositories.AccessoryRepository;
import app.repositories.PhotographerRepository;
import app.services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(AccessoryXml accessoriesXml) {

        Accessory accessory = this.modelParser.convert(accessoriesXml, Accessory.class);

        long photographersCount = this.photographerRepository.count();
        long photographerId =  ThreadLocalRandom.current().nextLong(1, photographersCount + 1);

        Photographer photographer = this.photographerRepository.findOne(photographerId);
        accessory.setOwner(photographer);
        this.accessoryRepository.saveAndFlush(accessory);
    }
}
