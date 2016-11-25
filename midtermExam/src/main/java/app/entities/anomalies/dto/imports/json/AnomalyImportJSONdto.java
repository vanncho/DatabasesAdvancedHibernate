package app.entities.anomalies.dto.imports.json;

import java.io.Serializable;

public class AnomalyImportJSONdto implements Serializable {

    private String originPlanet;

    private String teleportPlanet;

    public AnomalyImportJSONdto() {
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
}
