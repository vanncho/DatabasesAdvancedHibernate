package app.domains.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UsersCountJsonDtoExport implements Serializable {

    @Expose
    private int usersCount;

    @Expose
    List<UserProductsJsonDtoExport> users;

    public UsersCountJsonDtoExport() {
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserProductsJsonDtoExport> getUsers() {
        return users;
    }

    public void setUsers(List<UserProductsJsonDtoExport> users) {
        this.users = users;
    }
}
