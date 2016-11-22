package app.entities.planets;

import app.entities.anomalies.Anomaly;
import app.entities.solarSystems.SolarSystem;
import app.entities.stars.Star;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Expose
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "sun_id", referencedColumnName = "id")
    private Star sun;

    @ManyToOne
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    @OneToMany(mappedBy = "teleportPlanet", targetEntity = Anomaly.class, cascade = CascadeType.ALL)
    private Set<Anomaly> anomalies;

    public Planet() {
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

    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(Set<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
