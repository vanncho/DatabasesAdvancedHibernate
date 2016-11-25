package app.entities.persons;

import app.entities.anomalies.Anomaly;
import app.entities.planets.Planet;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @ManyToOne
    @JoinColumn(name = "home_planet_id", referencedColumnName = "id")
    private Planet homePlanet;

    @ManyToMany(mappedBy = "victims", targetEntity = Anomaly.class, cascade = CascadeType.ALL)
    private Set<Anomaly> anomalies;

    public Person() {
        this.setAnomalies(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(Planet homePlanet) {
        this.homePlanet = homePlanet;
    }

    public Set<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(Set<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
