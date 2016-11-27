package app.servicesImpl;

import app.domains.dtos.*;
import app.domains.entities.Product;
import app.domains.entities.User;
import app.repositories.UserRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(UserDto userDto) {
        User user = this.convertDtoToEntity(userDto);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        List<UserDto> usersJsonDtos = new ArrayList<>();

        for (User user : users) {
            UserDto currUserDto = new UserDto();
            currUserDto.setFirstName(user.getFirstName());
            currUserDto.setLastName(user.getLastName());
            currUserDto.setAge(user.getAge());
            usersJsonDtos.add(currUserDto);
        }
        return usersJsonDtos;
    }

    @Override
    public UserDto findUserById(long id) {
        User user = this.userRepository.findById(id);
        UserDto userDto = this.convertEntityToDto(user);
        return userDto;
    }

    @Override
    public List<UserBuyersJsonExport> usersWithSoldProducts() {
        List<User> users = this.userRepository.usersWithSoldProducts();

        List<UserBuyersJsonExport> usersJsonDtos = new ArrayList<>();

        for (User user : users) {
            UserBuyersJsonExport currUserDto = new UserBuyersJsonExport();
            currUserDto.setFirstName(user.getFirstName());
            currUserDto.setLastName(user.getLastName());

            Set<ProductJsonDtoExport> productJsonDtoExport = new HashSet<>();
            Set<Product> products = user.getProductsBought();
            for (Product product : products) {
                ProductJsonDtoExport pb = new ProductJsonDtoExport();
                pb.setName(product.getName());
                pb.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
                pb.setPrice(product.getPrice());
                productJsonDtoExport.add(pb);
            }

            currUserDto.setSoldProducts(productJsonDtoExport);
            usersJsonDtos.add(currUserDto);
        }
        return usersJsonDtos;
    }

    @Override
    public List<UsersCountDtoExport> usersAndProducts() {

        List<User> users = this.userRepository.usersAndProducts();
        List<UsersCountDtoExport> usersDto = new ArrayList<>();

        UsersCountDtoExport upDto = new UsersCountDtoExport();
        upDto.setUsersCount(users.size());

        List<UserProductsJsonDtoExport> pu = new ArrayList<>();

        for (User user : users) {

            UserProductsJsonDtoExport userProductsJsonDtoExport = new UserProductsJsonDtoExport();

            userProductsJsonDtoExport.setFirstName(user.getFirstName());
            userProductsJsonDtoExport.setLastName(user.getLastName());
            userProductsJsonDtoExport.setAge(user.getAge());

            Set<Product> products = user.getProductsSold();
            List<ProductUserCountJsonDtoExport> puc = new ArrayList<>();

            for (Product product : products) {
                ProductUserCountJsonDtoExport p = new ProductUserCountJsonDtoExport();
                p.setCount(products.size());

                List<ProductUserJsonDtoExport> prDto = new ArrayList<>();

                ProductUserJsonDtoExport ppp = new ProductUserJsonDtoExport();
                ppp.setName(product.getName());
                ppp.setPrice(product.getPrice());
                prDto.add(ppp);
                p.setProducts(prDto);
                puc.add(p);
            }

            userProductsJsonDtoExport.setSoldProducts(puc);
            pu.add(userProductsJsonDtoExport);

        }

        upDto.setUsers(pu);
        usersDto.add(upDto);

        return usersDto;
    }

    public UserDto convertEntityToDto(User user) {
        UserDto ujson = new UserDto();
        ujson.setId(user.getId());
        ujson.setFirstName(user.getFirstName());
        ujson.setLastName(user.getLastName());
        ujson.setAge(user.getAge());
        return ujson;
    }

    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        return user;
    }
}
