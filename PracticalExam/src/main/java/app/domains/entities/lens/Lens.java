package app.domains.entities.lens;

import app.domains.entities.photographers.Photographer;

import javax.persistence.*;

@Entity
@Table(name = "lens")
public class Lens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String make;

    @Column(name = "focal_length")
    private int focalLength;

    @Column(name = "max_aperture")
    private double maxAperture;

    @Transient
    private String concat;

    @Column(name = "compatible_with")
    private String compatibleWith;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Photographer owner;

    public Lens() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getConcat() {
        return this.make + " " + this.getFocalLength() + " " + this.getMaxAperture();
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
