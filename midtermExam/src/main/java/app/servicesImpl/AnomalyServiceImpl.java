package app.servicesImpl;

import app.entities.anomalies.Anomaly;
import app.repositories.AnomalyRepository;
import app.services.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnomalyServiceImpl implements AnomalyService {

    private final AnomalyRepository anomalyRepository;

    @Autowired
    public AnomalyServiceImpl(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    @Override
    public void create(Anomaly anomaly) {
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public List<Anomaly> findAllAnomalies() {
        return Collections.unmodifiableList(this.anomalyRepository.findAll());
    }

    @Override
    public Anomaly findAnomalyById(long id) {
        return this.anomalyRepository.findById(id);
    }

    @Override
    public void importDataInAnomalyVictims(long anId, long persId) {
        this.anomalyRepository.importDataInAnomalyVictims(anId, persId);
    }

    @Override
    public long findLastAnomalyId() {
        return this.anomalyRepository.findId();
    }

    @Override
    public List<Anomaly> findWithMostAffectedPeople() {
        return Collections.unmodifiableList(this.anomalyRepository.findWithMostAffectedPeople());
    }

    @Override
    public List<Anomaly> anomaliesAndPeopleAffected() {
        return Collections.unmodifiableList(this.anomalyRepository.anomaliesAndPeopleAffected());
    }
}
