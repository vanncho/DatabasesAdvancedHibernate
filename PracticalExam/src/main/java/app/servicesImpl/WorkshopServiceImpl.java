package app.servicesImpl;

import app.domains.dtos.input.WorkshopImportXml;
import app.domains.dtos.input.WorkshopParticipantsImportXml;
import app.domains.dtos.output.LocationExportXml;
import app.domains.dtos.output.ParticipantExportXml;
import app.domains.dtos.output.WorkshopsByLocationExportXml;
import app.domains.dtos.output.WorkshopsWithParticipantsExportXml;
import app.domains.entities.photographers.Photographer;
import app.domains.entities.workshops.Workshop;
import app.parsers.mapper.ModelParser;
import app.repositories.PhotographerRepository;
import app.repositories.WorkshopRepository;
import app.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(WorkshopImportXml workshopImportXml) {

        Workshop workshop = this.modelParser.convert(workshopImportXml, Workshop.class);

        List<WorkshopParticipantsImportXml> participants = workshopImportXml.getParticipant();

        Set<Photographer> photographers = new HashSet<>();

        for (WorkshopParticipantsImportXml participant : participants) {

            Photographer photographer = this.photographerRepository.findOneByFirstNameAndLastName(participant.getFirstName(), participant.getLastName());
            photographer.getParticipates().add(workshop);
            photographers.add(photographer);
        }

        workshop.setParticipants(photographers);
        String[] trainerFullName = workshopImportXml.getFullName().split("[\\s]+");
        Photographer trainer = this.photographerRepository.findOneByFirstNameAndLastName(trainerFullName[0], trainerFullName[1]);
        workshop.setTrainer(trainer);

        this.workshopRepository.saveAndFlush(workshop);
    }

    @Override
    public WorkshopsByLocationExportXml getWorkshopsByLocation() {

        List<Workshop> workshops = this.workshopRepository.getWorkshopsByLocation();

        WorkshopsByLocationExportXml workshopsByLocationExportXml = new WorkshopsByLocationExportXml();

        for (Workshop workshop : workshops) {

            WorkshopsWithParticipantsExportXml workshopsWithParticipantsExportXml = new WorkshopsWithParticipantsExportXml();
            Set<Photographer> participants = workshop.getParticipants();

            ParticipantExportXml participantExportXml = new ParticipantExportXml();

            for (Photographer participant : participants) {

                participantExportXml.getFullName().add(participant.getFullName());
            }

            participantExportXml.setCount(workshop.getParticipants().size());
            workshopsWithParticipantsExportXml.setName(workshop.getName());
            workshopsWithParticipantsExportXml.setTotalProfit(participantExportXml.getCount() * workshop.getPricePerParticipant() * 0.8);
            workshopsWithParticipantsExportXml.setPhotographers(participantExportXml);

            if (workshopsByLocationExportXml.getLocations().size() > 0 && workshopsByLocationExportXml.getLocations().stream().filter(l -> l.getLocationName().equals(workshop.getLocation())).findAny().orElse(null) != null) {
                workshopsByLocationExportXml.getLocations()
                        .stream()
                        .filter(l -> l.getLocationName()
                                .equals(workshop.getLocation()))
                        .findFirst()
                        .get()
                        .getWorkshopsWithParticipantsExportXml()
                        .add(workshopsWithParticipantsExportXml);
            } else {
                LocationExportXml locationExportXml = new LocationExportXml();
                locationExportXml.setLocationName(workshop.getLocation());
                locationExportXml.getWorkshopsWithParticipantsExportXml().add(workshopsWithParticipantsExportXml);
                workshopsByLocationExportXml.getLocations().add(locationExportXml);
            }
        }

        return workshopsByLocationExportXml;
    }
}
