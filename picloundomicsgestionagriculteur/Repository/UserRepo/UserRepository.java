package tn.esprit.picloundomicsgestionagriculteur.Repository.UserRepo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByUsername(String username);

    Optional<User> findByRole(String role);

    Optional<User> findByEmail(String email);



    public User findByConfirmationToken(String ConfirmationToken);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


}
