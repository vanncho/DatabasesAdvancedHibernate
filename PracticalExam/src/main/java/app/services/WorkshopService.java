package app.services;

import app.domains.dtos.input.WorkshopImportXml;
import app.domains.dtos.output.WorkshopsByLocationExportXml;

public interface WorkshopService {

    void create(WorkshopImportXml workshopImportXml);

    WorkshopsByLocationExportXml getWorkshopsByLocation();
}
