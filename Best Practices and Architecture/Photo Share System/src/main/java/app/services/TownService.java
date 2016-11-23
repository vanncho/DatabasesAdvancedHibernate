package app.services;

import app.domains.models.Town;

public interface TownService {

    void create(Town town);

    Town create(String name, String country);

    Town findTownByName(String name);
}
