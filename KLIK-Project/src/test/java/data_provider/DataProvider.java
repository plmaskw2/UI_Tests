package data_provider;

import com.github.javafaker.Faker;
import framework.model.Forum;
import framework.model.User;

public class DataProvider {

    public static String getMessageContent() {
        return new Faker().friends().quote();
    }

    public static User getNewUser() {
        String password = new Faker().number().digits(10);
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();
        return User.builder()
                .userName(firstName + lastName + new Faker().number().digits(6))
                .email("testEmail@com.com")
                .password(password)
                .confirmation(password)
                .firstName(firstName)
                .lastName(lastName)
                .headline(new Faker().commerce().department())
                .aboutYourself(new Faker().rickAndMorty().quote())
                .avatarPath(System.getProperty("user.dir") + "/src/test/java/data_provider/pictures/avatar.png")
                .build();
    }

    public static Forum getNewForum() {
        return Forum.builder()
                .topicSubject(new Faker().medical().diseaseName())
                .category("Technical Difficulties")
                .question(new Faker().medical().symptoms())
                .build();
    }
}
