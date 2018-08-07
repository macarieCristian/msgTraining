package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "roles")
@ToString(callSuper = true, exclude = "roles")
@Builder
@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS, query = "select distinct u from User u"),
        @NamedQuery(name = User.GET_USER_BY_USERNAME, query = "select u from User u where u.username=:username"),
        @NamedQuery(name = User.GET_USER_BY_EMAIL, query = "select u from User u where u.email=:email")
})
public class User extends BaseEntity<Long> {

    //named queries
    public static final String GET_ALL_USERS = "getAllUsers";
    public static final String GET_USER_BY_USERNAME = "getUserByUsername";
    public static final String GET_USER_BY_EMAIL = "getUserByEmail";

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobileNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();


}
