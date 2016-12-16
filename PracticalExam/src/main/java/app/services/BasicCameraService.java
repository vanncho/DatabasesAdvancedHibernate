package app.services;

import app.domains.dtos.input.CamerasImportJson;

public interface BasicCameraService {

    void create(CamerasImportJson camerasImportJson);
}
