package app.servicesImpl;

import app.domains.dtos.input.PhotographersImportJson;
import app.domains.dtos.output.*;
import app.domains.entities.cameras.BasicCamera;
import app.domains.entities.lens.Lens;
import app.domains.entities.photographers.Photographer;
import app.parsers.mapper.ModelParser;
import app.repositories.BasicCameraRepository;
import app.repositories.LensRepository;
import app.repositories.PhotographerRepository;
import app.services.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private BasicCameraRepository basicCameraRepository;

    @Autowired
    private LensRepository lensRepository;

    @Autowired
    private ModelParser modelParser;

    private int lensesLength;

    @Override
    public void create(PhotographersImportJson photographersImportJson) {

        long countOfCameras = this.basicCameraRepository.count();

        long primaryCameraId = ThreadLocalRandom.current().nextLong(1, countOfCameras + 1);
        long secondaryCameraId = ThreadLocalRandom.current().nextLong(1, countOfCameras + 1);

        BasicCamera primaryCamera = this.basicCameraRepository.findOne(primaryCameraId);
        BasicCamera secondaryCamera = this.basicCameraRepository.findOne(secondaryCameraId);

        Set<Lens> lenses = new HashSet<>();

        for (Long lenseId : photographersImportJson.getLenses()) {

            Lens lens = this.lensRepository.findOne(lenseId);

            if (lens != null && (lens.getCompatibleWith().equals(primaryCamera.getMake()) || lens.getCompatibleWith().equals(secondaryCamera.getMake()))) {
                lenses.add(lens);
            }
        }

        Photographer photographer = new Photographer();
        photographer.setFirstName(photographersImportJson.getFirstName());
        photographer.setLastName(photographersImportJson.getLastName());
        photographer.setPhone(photographersImportJson.getPhone());
        photographer.setLenses(lenses);
        photographer.setPrimaryCamera(primaryCamera);
        photographer.setSecondaryCamera(secondaryCamera);
        this.setLensesLength(lenses.size());

        for (Lens lense : lenses) {
            lense.setOwner(photographer);
        }

        this.photographerRepository.saveAndFlush(photographer);
    }

    public int getLensesLength() {
        return lensesLength;
    }

    public void setLensesLength(int lensesLength) {
        this.lensesLength = lensesLength;
    }

    @Override
    public List<PhotographersExportJson> getAllPhotographers() {

        List<Photographer> photographers = this.photographerRepository.getAllPhotographers();
        List<PhotographersExportJson> photographersExportJsons = new ArrayList<>();

        for (Photographer photographer : photographers) {

            PhotographersExportJson json = this.modelParser.convert(photographer, PhotographersExportJson.class);
            photographersExportJsons.add(json);
        }

        return photographersExportJsons;
    }

    @Override
    public List<LandscapePhotographersExportJson> getAllLandscapePhotographers() {

        List<Photographer> photographers = this.photographerRepository.getAllLandscapePhotographers();
        List<LandscapePhotographersExportJson> photographersExportJsons = new ArrayList<>();

        List<Photographer> filtered = this.getPhotographersWithDSLRCameras(photographers);

        for (Photographer photographer : filtered) {

            LandscapePhotographersExportJson json = this.modelParser.convert(photographer, LandscapePhotographersExportJson.class);
            json.setLensesCount(photographer.getLenses().size());
            json.setDslrCameraMake(photographer.getPrimaryCamera().getMake());
            photographersExportJsons.add(json);
        }

        return photographersExportJsons;
    }

    @Override
    public PhotographersWithCameraExportXml getPhotographersWithSameCameraMake() {

        List<Photographer> photographersWithCamera = this.photographerRepository.getPhotographersWithSameCameraMake();
        PhotographersWithCameraExportXml withCameraList = new PhotographersWithCameraExportXml();

        for (Photographer photographer : photographersWithCamera) {

            PhotographerWithCamera photographerWithCamera = this.modelParser.convert(photographer, PhotographerWithCamera.class);

            for (Lens lens : photographer.getLenses()) {

                photographerWithCamera.addLens(lens.getConcat());
            }

            withCameraList.addPhotographer(photographerWithCamera);
        }

        return withCameraList;
    }

    private List<Photographer> getPhotographersWithDSLRCameras(List<Photographer> photographers) {

        List<Photographer> filtered = new ArrayList<>();

        for (Photographer photographer : photographers) {

            Set<Lens> lenses = photographer.getLenses();
            boolean isEqualOrLessThan30 = true;

            for (Lens lense : lenses) {

                if (lense.getFocalLength() > 30) {
                    isEqualOrLessThan30 = false;
                }
            }

            if (!isEqualOrLessThan30) {
                continue;
            }

            filtered.add(photographer);
        }

        return filtered;
    }
}
