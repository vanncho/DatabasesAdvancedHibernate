package app.servicesImpl;

import app.domains.models.Picture;
import app.repositories.PictureRepository;
import app.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void create(Picture picture) {
        this.pictureRepository.save(picture);
    }

    @Override
    public Picture create(String title, String caption, String path) {
        Picture picture = new Picture();
        picture.setTitle(title);
        picture.setCaption(caption);
        picture.setPath(path);
        this.create(picture);
        return picture;
    }

    @Override
    public Picture findPictureByPath(String path) {
        return this.pictureRepository.findByPath(path);
    }
}
