package app.factories;

import app.domains.models.Town;

public class TownFactory {

    public Town create(String name, String country) {
        Town town = new Town();
        town.setName(name);
        town.setCountry(country);
        return town;
    }
}
