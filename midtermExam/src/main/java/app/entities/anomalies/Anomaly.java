package app.entities.anomalies;

import app.entities.persons.Person;
import app.entities.planets.Planet;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Expose
    @ManyToOne
    @JoinColumn(name = "origin_planet_id", referencedColumnName = "id")
    private Planet originPlanet;

    @Expose
    @ManyToOne
    @JoinColumn(name = "teleport_planet_id", referencedColumnName = "id")
    private Planet teleportPlanet;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "anomaly_victims",
            joinColumns = @JoinColumn(name = "anomaly_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    private Set<Person> victims;

    public Anomaly() {
        this.setVictims(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        return victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }
}
