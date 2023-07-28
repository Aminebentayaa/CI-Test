package tn.esprit.picloundomicsgestionagriculteur.Service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.picloundomicsgestionagriculteur.Repository.UserRepo.UserRepository;
import tn.esprit.picloundomicsgestionagriculteur.token.ConfirmationToken.ConfirmationTokenService;
import tn.esprit.picloundomicsgestionagriculteur.token.PasswordResetToken.PasswordTokenService;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUser, UserDetailsService {

    private final UserRepository userRepository;

    EmailServiceImpl emailService;

    ConfirmationTokenService confirmationTokenService;
    private final PasswordTokenService passwordTokenService;

    BCryptPasswordEncoder passwordEncoder;

    private final static String USER_NOT_MSG =
            "user with username % not found " ;


    @Override
    public User addUser(User user) {
        return userRepository.save(user) ;
    }

    @Override
    public User updateUser(Integer id, User newUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstname(newUser.getFirstname());
        existingUser.setLastname(newUser.getLastname());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setNumTel(newUser.getNumTel());
        existingUser.setUsername(newUser.getUsername());

        return userRepository.save(existingUser);
    }



    @Override
    public void deleteUser(Integer id) {
         userRepository.deleteById(id);
    }

    @Override
    public Optional<User> retrieveUserById(Integer id) {
       return userRepository.findById(id);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_MSG,username)));

    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }


    public void resetPassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(theUser);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        return passwordTokenService.validatePasswordResetToken(token);
    }

    @Override
    public User findUserByPasswordToken(String token) {
        return passwordTokenService.findUserByPasswordToken(token).get();
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String passwordResetToken) {
        passwordTokenService.createPasswordResetTokenForUser(user, passwordResetToken);
    }


}
