package app.domains.entities.workshops;

import app.domains.entities.photographers.Photographer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workshops")
public class Workshop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @Column(name = "location")
    private String location;

    @NotNull
    @Column(name = "price_per_participant")
    private Double pricePerParticipant;

    @NotNull
    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Photographer trainer;

    @ManyToMany(mappedBy = "participates", targetEntity = Photographer.class)
    private Set<Photographer> participants;

    public Workshop() {
        this.setParticipants(new HashSet<>());
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(Double pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public Photographer getTrainer() {
        return trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    public Set<Photographer> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Photographer> participants) {
        this.participants = participants;
    }
}
