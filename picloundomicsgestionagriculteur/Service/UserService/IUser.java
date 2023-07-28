package tn.esprit.picloundomicsgestionagriculteur.Service.UserService;

import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Optional;

public interface IUser {

    User addUser(User user);
    User updateUser(Integer id ,User user);
     void deleteUser(Integer id);
    Optional<User> retrieveUserById(Integer id);
    List<User> retrieveAllUsers();

    User loadUserByUsername(String name);

    String validatePasswordResetToken(String token);

    User findUserByPasswordToken(String token);

    void createPasswordResetTokenForUser(User user, String passwordResetToken);

}
