package app.services;

import app.domains.dtos.UserBuyersJsonExport;
import app.domains.dtos.UserDto;
import app.domains.dtos.UsersCountDtoExport;

import java.util.List;

public interface UserService {

    void create(UserDto user);

    List<UserDto> getAllUsers();

    UserDto findUserById(long id);

    List<UserBuyersJsonExport> usersWithSoldProducts();

    List<UsersCountDtoExport> usersAndProducts();
}
