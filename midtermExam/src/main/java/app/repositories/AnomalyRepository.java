package app.repositories;

import app.entities.anomalies.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {

    List<Anomaly> findAll();

    Anomaly findById(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO anomaly_victims(anomaly_id, person_id) VALUES(:anId, :persId)", nativeQuery = true)
    void importDataInAnomalyVictims(@Param(value = "anId") long anId, @Param(value = "persId") long persId);

    @Query(value = "SELECT a.id FROM anomalies AS a ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    long findId();

    @Query(value = "SELECT an FROM Anomaly AS an " +
                   "INNER JOIN an.victims AS av " +
                   "GROUP BY an.id " +
                   "ORDER BY COUNT(av.anomalies.size) DESC")
    List<Anomaly> findWithMostAffectedPeople();

    @Query(value = "SELECT a FROM Anomaly AS a INNER JOIN a.victims AS v")
    List<Anomaly> anomaliesAndPeopleAffected();
}
