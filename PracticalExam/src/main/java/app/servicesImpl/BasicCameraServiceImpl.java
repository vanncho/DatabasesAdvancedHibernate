package app.servicesImpl;

import app.domains.dtos.input.CamerasImportJson;
import app.domains.entities.cameras.BasicCamera;
import app.domains.entities.cameras.DSLRCamera;
import app.domains.entities.cameras.MirrorlessCamera;
import app.parsers.mapper.ModelParser;
import app.repositories.BasicCameraRepository;
import app.services.BasicCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicCameraServiceImpl implements BasicCameraService {

    @Autowired
    private BasicCameraRepository basicCameraRepository;

    @Override
    public void create(CamerasImportJson camerasImportJson) {

        String cameraType = camerasImportJson.getType();
        BasicCamera basicCamera = null;

        switch (cameraType) {

            case "DSLR":
                basicCamera = new DSLRCamera();
                break;
            case "Mirrorless":
                basicCamera = new MirrorlessCamera();
                break;
        }

        basicCamera.setType(camerasImportJson.getType());
        basicCamera.setMake(camerasImportJson.getMake());
        basicCamera.setModel(camerasImportJson.getModel());
        basicCamera.setIsFullFrame(camerasImportJson.getIsFullFrame());
        basicCamera.setMinISO(camerasImportJson.getMinISO());
        basicCamera.setMaxISO(camerasImportJson.getMaxISO());
        basicCamera.setMaxShutterSpeed(camerasImportJson.getMaxShutterSpeed());
        basicCamera.setMaxVideoResolution(camerasImportJson.getMaxVideoResolution());
        basicCamera.setMaxFrameRate(camerasImportJson.getMaxFrameRate());

        this.basicCameraRepository.saveAndFlush(basicCamera);
    }
}
