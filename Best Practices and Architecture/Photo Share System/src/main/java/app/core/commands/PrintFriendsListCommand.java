package app.core.commands;

import app.domains.models.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class PrintFriendsListCommand extends Command {

    @Autowired
    private UserService userService;

    protected PrintFriendsListCommand(String[] data) {
        super(data);
    }
    /**
     * PrintFriendsList <username>
     * prints all friends of user with given username
     */
    @Override
    public String Execute() {

        String username = this.getData()[1];
        User user = this.userService.findUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException(String.format("No user found with username: %s", username));
        }

        Set<User> friends = user.getFriends();

        System.out.printf("Friends of %s: %n", username);
        for (User friend : friends) {

            System.out.println(friend.getUsername());
        }

        return "End of list.";
    }
}
