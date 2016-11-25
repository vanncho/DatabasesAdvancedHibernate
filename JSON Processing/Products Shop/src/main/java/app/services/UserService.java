package app.services;

import app.domains.dtos.UserBuyersJsonExport;
import app.domains.dtos.UserJsonDto;
import app.domains.dtos.UsersCountJsonDtoExport;

import java.util.List;

public interface UserService {

    void create(UserJsonDto user);

    List<UserJsonDto> getAllUsers();

    UserJsonDto findUserById(long id);

    List<UserBuyersJsonExport> usersWithSoldProducts();

    List<UsersCountJsonDtoExport> usersAndProducts();
}
