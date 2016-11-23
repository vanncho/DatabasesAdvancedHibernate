package app.factories;

import app.domains.models.Picture;
import app.domains.models.Town;
import app.domains.models.User;

import java.util.Date;

public class UserFactory {

    public User create(String username,
                       String password,
                       String email,
                       Picture profilePicture,
                       String firstName,
                       String lastName,
                       Town bornTown,
                       Town currentTown,
                       Date registeredOn,
                       Date lastTimeLoggedIn,
                       Integer age,
                       Boolean isDeleted) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setProfilePicture(profilePicture);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBornTown(bornTown);
        user.setCurrentTown(currentTown);
        user.setRegisteredOn(registeredOn);
        user.setLastTimeLoggedIn(lastTimeLoggedIn);
        user.setAge(age);
        user.setDeleted(isDeleted);
        return user;
    }
}
