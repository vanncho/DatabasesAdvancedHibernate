package app.services;

import app.entities.anomalies.Anomaly;

import java.util.List;

public interface AnomalyService {

    void create(Anomaly anomaly);

    List<Anomaly> findAllAnomalies();

    Anomaly findAnomalyById(long id);

    void importDataInAnomalyVictims(long anId, long persId);

    long findLastAnomalyId();

    List<Anomaly> findWithMostAffectedPeople();

    List<Anomaly> anomaliesAndPeopleAffected();
}
