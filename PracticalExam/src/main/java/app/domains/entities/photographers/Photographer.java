package app.domains.entities.photographers;

import app.annotations.Phone;
import app.domains.entities.accessories.Accessory;
import app.domains.entities.cameras.BasicCamera;
import app.domains.entities.lens.Lens;
import app.domains.entities.workshops.Workshop;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;


    @Size(min = 2, max = 50)
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Phone
    @Column(name = "phone")
    private String phone;

    @NotNull
    @OneToOne
    @JoinColumn(name = "primary_camera_id")
    private BasicCamera primaryCamera;

    @NotNull
    @OneToOne
    @JoinColumn(name = "secondary_camera_id")
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner", targetEntity = Lens.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Lens> lenses;

    @OneToMany(mappedBy = "owner", targetEntity = Accessory.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Accessory> accessories;

    @ManyToMany
    @JoinTable(name = "photographers_workshops",
            joinColumns = @JoinColumn(name = "photographer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "id")
    )
    private Set<Workshop> participates;

    public Photographer() {
        this.setLenses(new HashSet<>());
        this.setAccessories(new HashSet<>());
        this.setParticipates(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Lens> getLenses() {
        return lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Workshop> getParticipates() {
        return participates;
    }

    public void setParticipates(Set<Workshop> participates) {
        this.participates = participates;
    }
}
