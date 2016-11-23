package app.core.commands;

import app.domains.models.Town;
import app.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTownCommand extends Command {

    @Autowired
    private TownService townService;

    protected AddTownCommand(String[] data) {
        super(data);
    }

    /**
     *  AddTown <townName> <countryName>
     */
    @Override
    public String Execute() {

        String townName = this.getData()[1];
        String countryName = this.getData()[2];

        Town town = this.townService.create(townName, countryName);
        this.townService.create(town);

        return townName + " was added to database";
    }
}
