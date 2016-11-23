package app.core.commands;

import app.domains.models.Town;
import app.domains.models.User;
import app.services.TownService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyUserCommand extends Command{

    @Autowired
    private UserService userService;

    @Autowired
    private TownService townService;

    protected ModifyUserCommand(String[] data) {
        super(data);
    }
    /**
     * ModifyUser <username> <property> <new value>
     * For example:
     * ModifyUser <username> Password <NewPassword>
     * ModifyUser <username> Email <NewEmail>
     * ModifyUser <username> FirstName <NewFirstName>
     * ModifyUser <username> LastName <newLastName>
     * ModifyUser <username> BornTown <newBornTownName>
     * ModifyUser <username> CurrentTown <newCurrentTownName>
     * !!! Cannot change username
     */
    @Override
    public String Execute() {

        String username = this.getData()[1];
        String property = this.getData()[2];
        String propertyValue = this.getData()[3];

        User user = this.userService.findUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException(String.format("No user found with username: %s", username));
        }

        switch (property) {

            case "password": user.setPassword(propertyValue);
                break;
            case "email": user.setEmail(propertyValue);
                break;
            case "firstName": user.setFirstName(propertyValue);
                break;
            case "lastName": user.setLastName(propertyValue);
                break;
            case "bornTown": {

                Town town = getTown(propertyValue);
                user.setBornTown(town);

            } break;
            case "currentTown": {

                Town town = getTown(propertyValue);
                user.setCurrentTown(town);

            } break;
            case "age": user.setAge(Integer.valueOf(propertyValue));
                break;
            case "isDeleted": user.setDeleted(Boolean.valueOf(propertyValue));
                break;
            default: throw new IllegalArgumentException("No such property or unmodifiable property.");
        }

        this.userService.create(user);
        return String.format("Property %s changed to %s.", property, propertyValue);
    }

    private Town getTown(String propertyValue) {
        Town town = this.townService.findTownByName(propertyValue);

        if (town == null) {
            town = this.townService.create(propertyValue, null);
        }
        return town;
    }
}
