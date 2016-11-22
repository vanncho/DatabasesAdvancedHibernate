package app.commander;

import app.entities.anomalies.Anomaly;
import app.entities.anomalies.dto.exports.json.AnomalyWithVictimExportJSONdto;
import app.entities.anomalies.dto.exports.xml.AnomalyExportListXMLDto;
import app.entities.anomalies.dto.exports.xml.AnomalyExportXMLDto;
import app.entities.anomalies.dto.exports.xml.AnomalyWithVictimImportXMLDto;
import app.entities.anomalies.dto.exports.xml.VictimExportXMLDto;
import app.entities.anomalies.dto.imports.json.AnomalyImportJSONdto;
import app.entities.anomalies.dto.imports.json.AnomalyVictimsImportJSONdto;
import app.entities.anomalies.dto.imports.xml.AnomalyXMLImportDto;
import app.entities.persons.Person;
import app.entities.persons.dto.exports.json.PersonExportJSONdto;
import app.entities.persons.dto.imports.json.PersonImportJSONdto;
import app.entities.persons.dto.imports.xml.VictimImportXMLDto;
import app.entities.planets.Planet;
import app.entities.planets.dto.exports.json.PlanetExportJSONdto;
import app.entities.planets.dto.imports.json.PlanetImportJSONdto;
import app.entities.solarSystems.SolarSystem;
import app.entities.solarSystems.dto.imports.json.SolarSystemImportJSONdto;
import app.entities.stars.Star;
import app.entities.stars.dto.imports.json.StarImportJSONdto;
import app.services.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class TerminalCommander implements CommandLineRunner {

    private final AnomalyService anomalyService;

    private final PersonService personService;

    private final PlanetService planetService;

    private final SolarSystemService solarSystemService;

    private final StarService starService;

    @Autowired
    public TerminalCommander(AnomalyService anomalyService,
                             PersonService personService,
                             PlanetService planetService,
                             SolarSystemService solarSystemService,
                             StarService starService) {
        this.anomalyService = anomalyService;
        this.personService = personService;
        this.planetService = planetService;
        this.solarSystemService = solarSystemService;
        this.starService = starService;
    }

    @Override
    public void run(String... strings) throws Exception {

        // Read from JSON
        String jsonFile = "solar-systems.json";
        solarSystemReadFromJSON(jsonFile);

        jsonFile = "stars.json";
        starReadFromJSON(jsonFile);

        jsonFile = "planets.json";
        planetReadFromJSON(jsonFile);

        jsonFile = "persons.json";
        personReadFromJSON(jsonFile);

        jsonFile = "anomalies.json";
        anomalyReadFromJSON(jsonFile);

        jsonFile = "anomaly-victims.json";
        anomalyVictimsReadFromJSON(jsonFile);

        // Read from XML
        String xmlFile = "new-anomalies.xml";
        anomaliesVictimsReadFromXML(xmlFile);

        // Write to JSON
        exportPlanetToJSON();

        exportPeopleToJSON();

        exportAnomalyWhichAffectedMostPeople();

        // Write to XML
        exportAnomaliesAndPeopleAffected();
    }

    private void exportAnomaliesAndPeopleAffected() throws JAXBException {

        List<Anomaly> anomEport = this.anomalyService.anomaliesAndPeopleAffected();

        AnomalyExportListXMLDto anomalyExportListXMLDto = new AnomalyExportListXMLDto();
        List<AnomalyExportXMLDto> toExport = new ArrayList<>();

        for (int i = 0; i < anomEport.size(); i++) {

            Anomaly currAnom = anomEport.get(i);
            AnomalyExportXMLDto currDto = new AnomalyExportXMLDto();

            currDto.setId(currAnom.getId());
            currDto.setOriginPlanet(currAnom.getOriginPlanet().getName());
            currDto.setTeleportPlanet(currAnom.getTeleportPlanet().getName());

            Set<Person> currPersons = currAnom.getVictims();
            List<VictimExportXMLDto> pDto = new ArrayList<>();

            for (Person currPerson : currPersons) {

                VictimExportXMLDto victimDto = new VictimExportXMLDto();
                victimDto.setVictim(currPerson.getName());

                pDto.add(victimDto);
            }

            currDto.setVictims(pDto);
            toExport.add(currDto);
        }

        anomalyExportListXMLDto.setAnomalyExportXMLDtoList(toExport);

        JAXBContext jaxbContext = JAXBContext.newInstance(anomalyExportListXMLDto.getClass());

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File outputXMLFile = new File("src/main/resources/files/output/xml/anomalies.xml");
        jaxbMarshaller.marshal(anomalyExportListXMLDto, outputXMLFile);
    }

    private void exportAnomalyWhichAffectedMostPeople() throws IOException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        List<Anomaly> anom = this.anomalyService.findWithMostAffectedPeople();

        for (int i = 0; i < 1; i++) {

            AnomalyWithVictimExportJSONdto anvDto = new AnomalyWithVictimExportJSONdto();
            anvDto.setId(anom.get(i).getId());
            anvDto.setOriginPlanet(anom.get(i).getOriginPlanet().getName());
            anvDto.setTeleportPlanet(anom.get(i).getTeleportPlanet().getName());
            anvDto.setVictims(anom.get(i).getVictims().size());

            String authorJson = gson.toJson(anvDto);
            File outputFile = new File("src/main/resources/files/output/json/anomaly.json");

            try (
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
            ) {
                bufferedWriter.write(authorJson);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void exportPeopleToJSON() throws IOException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        List<Person> persons = this.personService.findPersonsNonVictimsOfAnomalies();

        for (int i = 0; i < persons.size(); i++) {

            PersonExportJSONdto ped = new PersonExportJSONdto();
            ped.setName(persons.get(i).getName());

            String authorJson = gson.toJson(persons);
            File outputFile = new File("src/main/resources/files/output/json/persons.json");

            try (
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
            ) {
                bufferedWriter.write(authorJson);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void exportPlanetToJSON() throws IOException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        List<Planet> planets = this.planetService.findWithNoTeleportedPeople();

        for (int i = 0; i < planets.size(); i++) {

            PlanetExportJSONdto ped = new PlanetExportJSONdto();
            ped.setName(planets.get(i).getName());

            String authorJson = gson.toJson(planets);
            File outputFile = new File("src/main/resources/files/output/json/planets.json");

            try (
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
            ) {
                bufferedWriter.write(authorJson);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void anomaliesVictimsReadFromXML(String xmlFile) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(AnomalyXMLImportDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File outputXMLFile = new File("src/main/resources/files/input/xml/" + xmlFile);
        AnomalyXMLImportDto anomaliesFromXML = (AnomalyXMLImportDto) unmarshaller.unmarshal(outputXMLFile);

        List<AnomalyWithVictimImportXMLDto> anomalies = anomaliesFromXML.getAnomalies();

        for (int i = 0; i < anomalies.size(); i++) {

            AnomalyWithVictimImportXMLDto current = anomalies.get(i);

            Planet originPlanet = this.planetService.findPlanetByName(current.getOriginPlanet());
            Planet teleportPlanet = this.planetService.findPlanetByName(current.getTeleportPlanet());
            List<VictimImportXMLDto> victimsList = current.getVictimName();

            if (originPlanet != null && teleportPlanet != null && victimsList.size() > 0) {

                Anomaly anomaly = new Anomaly();
                anomaly.setOriginPlanet(originPlanet);
                anomaly.setTeleportPlanet(teleportPlanet);
                this.anomalyService.create(anomaly);

                long anomalyId = this.anomalyService.findLastAnomalyId();
                for (int j = 0; j < victimsList.size(); j++) {

                    VictimImportXMLDto currentVictim = victimsList.get(j);
                    Person victim = this.personService.findPersonByName(currentVictim.getVictim());
                    this.anomalyService.importDataInAnomalyVictims(anomalyId, victim.getId());
                    System.out.println("Successfully imported data.");
                }
            } else {
                System.out.println("Error: Invalid data.");
            }
        }
    }

    private void anomalyVictimsReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<AnomalyVictimsImportJSONdto> anomalyVictims = Arrays.asList(gson.fromJson(jsonData.toString(), AnomalyVictimsImportJSONdto[].class));

            for (AnomalyVictimsImportJSONdto anomalyVictimDto : anomalyVictims) {

                Anomaly anomaly = this.anomalyService.findAnomalyById(anomalyVictimDto.getId());
                Person person = this.personService.findPersonByName(anomalyVictimDto.getPerson());

                if (person != null && anomaly != null) {

                    long anId = anomaly.getId();
                    long persId = person.getId();
                    this.anomalyService.importDataInAnomalyVictims(anId, persId);

                    System.out.println("Successfully imported AnomalyVictim.");

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            {
            }
        }
    }

    private void anomalyReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<AnomalyImportJSONdto> anomalies = Arrays.asList(gson.fromJson(jsonData.toString(), AnomalyImportJSONdto[].class));

            for (AnomalyImportJSONdto anomalyDto : anomalies) {

                Planet originPlanet = this.planetService.findPlanetByName(anomalyDto.getOriginPlanet());
                Planet teleportPlanet = this.planetService.findPlanetByName(anomalyDto.getTeleportPlanet());

                if (originPlanet != null && teleportPlanet != null) {

                    Anomaly anomaly = new Anomaly();
                    anomaly.setOriginPlanet(originPlanet);
                    anomaly.setTeleportPlanet(teleportPlanet);

                    this.anomalyService.create(anomaly);
                    System.out.println("Successfully imported Anomaly.");

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            {
            }
        }
    }


    private void personReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<PersonImportJSONdto> persons = Arrays.asList(gson.fromJson(jsonData.toString(), PersonImportJSONdto[].class));

            for (PersonImportJSONdto personDto : persons) {

                if (personDto.getName() != null) {

                    Planet homePlanet = this.planetService.findPlanetByName(personDto.getHomePlanet());

                    if (homePlanet != null) {

                        Person person = new Person();
                        person.setName(personDto.getName());
                        person.setHomePlanet(homePlanet);

                        this.personService.create(person);
                        System.out.printf("Successfully imported %s.%n", person.getName());
                    }

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void planetReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<PlanetImportJSONdto> planets = Arrays.asList(gson.fromJson(jsonData.toString(), PlanetImportJSONdto[].class));

            for (PlanetImportJSONdto planetDto : planets) {

                if (planetDto.getName() != null && planetDto.getSolarSystem() != null && planetDto.getSun() != null) {

                    SolarSystem solarSystem = this.solarSystemService.findSolarSystemByName(planetDto.getSolarSystem());
                    Star sun = this.starService.findStarByName(planetDto.getSun());
                    Planet planet = new Planet();
                    planet.setName(planetDto.getName());
                    planet.setSun(sun);
                    planet.setSolarSystem(solarSystem);

                    this.planetService.create(planet);
                    System.out.printf("Successfully imported %s.%n", planet.getName());

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void starReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<StarImportJSONdto> stars = Arrays.asList(gson.fromJson(jsonData.toString(), StarImportJSONdto[].class));

            for (StarImportJSONdto starDto : stars) {

                if (starDto.getName() != null && starDto.getSolarSystem() != null) {

                    SolarSystem solarSystem = this.solarSystemService.findSolarSystemByName(starDto.getSolarSystem());
                    Star star = new Star();
                    star.setName(starDto.getName());
                    star.setSolarSystem(solarSystem);

                    this.starService.create(star);
                    System.out.printf("Successfully imported %s.%n", star.getName());

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void solarSystemReadFromJSON(String jsonFile) throws IOException {

        Gson gson = new Gson();

        InputStream inputStream = getClass().getResourceAsStream("/files/input/json/" + jsonFile);
        StringBuilder jsonData = new StringBuilder();

        try (
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }

            List<SolarSystemImportJSONdto> solarSystems = Arrays.asList(gson.fromJson(jsonData.toString(), SolarSystemImportJSONdto[].class));

            for (SolarSystemImportJSONdto systemDto : solarSystems) {

                if (systemDto.getName() != null) {

                    SolarSystem system = new SolarSystem();
                    system.setName(systemDto.getName());

                    this.solarSystemService.create(system);
                    System.out.printf("Successfully imported %s %s.%n", SolarSystem.class.getSimpleName(), system.getName());

                } else {
                    System.out.println("Error: Invalid data.");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
