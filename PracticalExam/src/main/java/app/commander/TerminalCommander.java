package app.commander;

import app.domains.dtos.input.*;
import app.domains.dtos.output.*;
import app.io.write.Writer;
import app.parsers.json.JsonParser;
import app.parsers.xml.XmlParser;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class TerminalCommander implements CommandLineRunner {

    @Autowired
    private LensService lensService;

    @Autowired
    private BasicCameraService basicCameraService;

    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private XmlParser xmlParser;

    @Autowired
    private Writer writer;

    public TerminalCommander() {
    }

    @Override
    public void run(String... strings) throws Exception {

        this.seedData();
    }

    private void seedData() {

        //this.importData();

        this.exportData();
    }

    private void exportData() {

        //this.exportPhotographers();

        //this.exportLandscapePhotographers();

        //this.exportPhotographersWithSameCameraMake();

        this.exportWorkshopsByLocation();

    }

    private void exportWorkshopsByLocation() {

        WorkshopsByLocationExportXml workshopsByLocationExportXml = this.workshopService.getWorkshopsByLocation();

        try {
            this.xmlParser.write(workshopsByLocationExportXml, "src/main/resources/files/output/xml/workshops-by-location.xml");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void exportPhotographersWithSameCameraMake() {

        PhotographersWithCameraExportXml exportPhotoXml = this.photographerService.getPhotographersWithSameCameraMake();

        try {
            this.xmlParser.write(exportPhotoXml, "src/main/resources/files/output/xml/same-cameras-photographers.xml");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void exportLandscapePhotographers() {

        List<LandscapePhotographersExportJson> photographers = this.photographerService.getAllLandscapePhotographers();

        try {
            this.jsonParser.exportJson(photographers, "src/main/resources/files/output/json/landscape-photogaphers.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportPhotographers() {

        List<PhotographersExportJson> photographers = this.photographerService.getAllPhotographers();

        try {
            this.jsonParser.exportJson(photographers, "src/main/resources/files/output/json/photographers-ordered.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importData() {

//        this.importLenses();
//
//        this.importCameras();
//
//        this.importPhotographers();
//
//        this.importAccessories();
//
//        this.importWorkshops();
    }

    private void importWorkshops() {

        WorkshopsXml workshopImportXml = null;

        try {
            workshopImportXml = this.xmlParser.read(WorkshopsXml.class, "/files/input/xml/workshops.xml");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (WorkshopImportXml importXml : workshopImportXml.getWorkshops()) {

            try {
                this.workshopService.create(importXml);
                this.writer.writeLine("Successfully imported " + importXml.getName());
            } catch (Exception e) {
                this.writer.writeLine("Error: Invalid data.");
            }
        }
    }

    private void importAccessories() {

        AccessoriesImportXml accessoriesImportXml = null;

        try {
            accessoriesImportXml = this.xmlParser.read(AccessoriesImportXml.class, "/files/input/xml/accessories.xml");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (AccessoryXml accessoryXml : accessoriesImportXml.getAccessories()) {

            try {
                this.accessoryService.create(accessoryXml);
                this.writer.writeLine("Successfully imported " + accessoryXml.getName());

            } catch (Exception e) {
                this.writer.writeLine("Error: Invalid data.");
            }
        }
    }

    private void importPhotographers() {

        PhotographersImportJson[] photographersImportJsons = null;

        try {
            photographersImportJsons = this.jsonParser.importJson(PhotographersImportJson[].class, "/files/input/json/photographers.json");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (PhotographersImportJson photographersImportJson : photographersImportJsons) {

            try {
                this.photographerService.create(photographersImportJson);
                this.writer.writeLine("Successfully imported " + photographersImportJson.getFirstName() + " | " + "Lenses: " + this.photographerService.getLensesLength());

            } catch (Exception e) {
                this.writer.writeLine("Error. Invalid data provided");
            }
        }
    }

    private void importCameras() {

        CamerasImportJson[] camerasImportJsons = null;

        try {
            camerasImportJsons = this.jsonParser.importJson(CamerasImportJson[].class, "/files/input/json/cameras.json");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (CamerasImportJson camerasImportJson : camerasImportJsons) {

            try {
                this.basicCameraService.create(camerasImportJson);
                this.writer.writeLine("Successfully imported " + camerasImportJson.getType() + " " + camerasImportJson.getMake() + " " + camerasImportJson.getMinISO());

            } catch (Exception e) {
                this.writer.writeLine("Error. Invalid data provided");
            }
        }
    }

    private void importLenses() {

        LensesImportJson[] lensesImportJsons = null;

        try {
            lensesImportJsons = this.jsonParser.importJson(LensesImportJson[].class, "/files/input/json/lenses.json");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (LensesImportJson lensesImportJson : lensesImportJsons) {

            try {
                this.lensService.create(lensesImportJson);
                this.writer.writeLine("Successfully imported " + lensesImportJson.getMake() + " " + lensesImportJson.getFocalLength() + " " + lensesImportJson.getMaxAperture());

            } catch (Exception e) {
                this.writer.writeLine("Error. Invalid data provided");
            }
        }
    }
}
