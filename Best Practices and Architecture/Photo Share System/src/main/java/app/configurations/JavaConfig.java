package app.configurations;

import app.factories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public AlbumFactory albumFactory() {
        return new AlbumFactory();
    }

    @Bean
    public AlbumRoleFactory albumRoleFactory(){
        return new AlbumRoleFactory();
    }

    @Bean
    public PictureFactory pictureFactory() {
        return new PictureFactory();
    }

    @Bean
    public TagFactory tagFactory() {
        return new TagFactory();
    }

    @Bean
    public TownFactory townFactory() {
        return new TownFactory();
    }

    @Bean
    public UserFactory userFactory() {
        return new UserFactory();
    }
}
