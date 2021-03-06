package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.UserStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Api for user persistence
 */
public interface UserDao {

    Optional<User> addUser(@NotNull User user);

    Optional<User> updateUser(@NotNull User user);

    List<User> getAllUsers();

    Optional<User> getUserByUsername(@NotNull String username);

    Boolean setUserStatus(Long id, UserStatus userStatus);

    Optional<User> getUserWithEmail(@NotNull String email);

}
