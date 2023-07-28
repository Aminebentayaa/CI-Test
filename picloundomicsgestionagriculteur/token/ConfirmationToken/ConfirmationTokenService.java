package tn.esprit.picloundomicsgestionagriculteur.token.ConfirmationToken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.picloundomicsgestionagriculteur.token.ConfirmationToken.ConfirmationToken;
import tn.esprit.picloundomicsgestionagriculteur.token.ConfirmationToken.ConfirmationTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;



    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
