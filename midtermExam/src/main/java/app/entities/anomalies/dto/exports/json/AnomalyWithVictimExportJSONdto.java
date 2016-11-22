package app.entities.anomalies.dto.exports.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyWithVictimExportJSONdto implements Serializable {

    @Expose
    private long id;

    @Expose
    private String originPlanet;

    @Expose
    private String teleportPlanet;

    @Expose
    private int victims;

    public AnomalyWithVictimExportJSONdto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public int getVictims() {
        return victims;
    }

    public void setVictims(int victims) {
        this.victims = victims;
    }
}
