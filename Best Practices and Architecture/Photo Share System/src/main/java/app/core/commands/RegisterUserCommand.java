package app.core.commands;

import app.domains.models.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RegisterUserCommand extends Command {

    @Autowired
    private UserService userService;

    protected RegisterUserCommand(String[] data) {
        super(data);
    }
    /**
     * RegisterUser <username> <password> <repeat-password> <email>
     */
    @Override
    public String Execute() {

        String username = this.getData()[1];
        String password = this.getData()[2];
        String repeatPassword = this.getData()[3];
        String email = this.getData()[4];

        if (password.equals(repeatPassword)) {
            throw new UnsupportedOperationException("Passwords does not match");
        }

        User user = this.userService.create(username,
                                            password,
                                            email,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null,
                                            new Date(),
                                            new Date(),
                                            null,
                                            false);

        this.userService.create(user);
        return "User " + user.getUsername() + " was registered successfully.";
    }
}
