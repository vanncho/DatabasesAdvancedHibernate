package app.services;

import app.domains.models.Picture;
import app.domains.models.Town;
import app.domains.models.User;

import java.util.Date;

public interface UserService {

    void create(User user);

    User create(String username,
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
                Boolean isDeleted);

    User findUserByUsername(String username);

    User findUserById(Integer id);
}
