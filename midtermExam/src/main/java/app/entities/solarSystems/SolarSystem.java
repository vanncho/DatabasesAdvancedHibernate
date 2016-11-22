package app.entities.solarSystems;

import app.entities.stars.Star;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @OneToMany(mappedBy = "solarSystem", targetEntity = Star.class, cascade = CascadeType.ALL)
    private Set<Star> stars;

    public SolarSystem() {
        this.setStars(new HashSet<>());
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

    public Set<Star> getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }
}
