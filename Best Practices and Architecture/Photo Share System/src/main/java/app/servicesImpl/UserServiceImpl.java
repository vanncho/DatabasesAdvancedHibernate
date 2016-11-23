package app.servicesImpl;

import app.domains.models.Picture;
import app.domains.models.Town;
import app.domains.models.User;
import app.factories.UserFactory;
import app.repositories.UserRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public void create(User user) {
        this.userRepository.save(user);
    }

    @Override
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

        User user = this.userFactory.create(username,
                                            password,
                                            email,
                                            profilePicture,
                                            firstName,
                                            lastName,
                                            bornTown,
                                            currentTown,
                                            registeredOn,
                                            lastTimeLoggedIn,
                                            age,
                                            isDeleted);
        this.create(user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Integer id) {
        return this.userRepository.findById(id);
    }
}
