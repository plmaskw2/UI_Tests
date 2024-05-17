package framework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class User {
    private String userName;
    private String email;
    private String password;
    private String confirmation;
    private String firstName;
    private String lastName;
    private String headline;
    private String aboutYourself;
    private String avatarPath;
}
