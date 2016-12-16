package app.services;

import app.domains.dtos.input.PhotographersImportJson;
import app.domains.dtos.output.LandscapePhotographersExportJson;
import app.domains.dtos.output.PhotographersExportJson;
import app.domains.dtos.output.PhotographersWithCameraExportXml;

import java.util.List;

public interface PhotographerService {

    void create(PhotographersImportJson photographersImportJson);

    int getLensesLength();

    void setLensesLength(int lensesLength);

    List<PhotographersExportJson> getAllPhotographers();

    List<LandscapePhotographersExportJson> getAllLandscapePhotographers();

    PhotographersWithCameraExportXml getPhotographersWithSameCameraMake();
}
