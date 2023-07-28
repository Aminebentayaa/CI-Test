package tn.esprit.picloundomicsgestionagriculteur.token.PasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picloundomicsgestionagriculteur.token.ConfirmationToken.ConfirmationToken;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken,Long> {

public PasswordResetToken findByToken(String token);
}
