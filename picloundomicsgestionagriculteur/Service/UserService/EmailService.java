package tn.esprit.picloundomicsgestionagriculteur.Service.UserService;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendConfirmationEmail(String to, String token);
    void send(String to, String email);



}
