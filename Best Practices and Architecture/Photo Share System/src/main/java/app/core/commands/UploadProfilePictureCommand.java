package app.core.commands;


import app.domains.models.Picture;
import app.domains.models.User;
import app.services.PictureService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadProfilePictureCommand extends Command {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private UserService userService;

    protected UploadProfilePictureCommand(String[] data) {
        super(data);
    }
    /**
     * UploadProfilePicture <username> <pictureFilePath>
     */
    @Override
    public String Execute() {

        String username = this.getData()[1];
        String picturePath = this.getData()[2];

        User user = this.userService.findUserByUsername(username);
        Picture picture = this.pictureService.findPictureByPath(picturePath);

        if (user == null || picture == null) {
            throw new IllegalArgumentException("No picture or user found.");
        }

        user.setProfilePicture(picture);
        this.userService.create(user);
        return "Profile picture uploaded.";
    }
}
