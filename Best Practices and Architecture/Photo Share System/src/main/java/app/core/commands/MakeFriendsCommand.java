package app.core.commands;

import app.domains.models.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class MakeFriendsCommand extends Command {

    @Autowired
    private UserService userService;

    protected MakeFriendsCommand(String[] data) {
        super(data);
    }
    /**
     * bidirectional adding friends
     * MakeFriends <username1> <username2>
     */
    @Override
    public String Execute() {

        String userOne = this.getData()[1];
        String userTwo = this.getData()[2];

        User one = this.userService.findUserByUsername(userOne);
        User two = this.userService.findUserByUsername(userTwo);

        if (one == null || two == null) {
            throw new IllegalArgumentException("Needed two valid users.");
        }

        one.getFriends().add(two);
        two.getFriends().add(one);

        this.userService.create(one);
        this.userService.create(two);

        return String.format("User %s and user %s become friends.", one.getUsername(), two.getUsername());
    }
}
