package tn.esprit.picloundomicsgestionagriculteur.auth;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picloundomicsgestionagriculteur.Config.LogoutService;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import tn.esprit.picloundomicsgestionagriculteur.Repository.UserRepo.UserRepository;
import tn.esprit.picloundomicsgestionagriculteur.Service.UserService.EmailServiceImpl;
import tn.esprit.picloundomicsgestionagriculteur.Service.UserService.UserServiceImp;
import tn.esprit.picloundomicsgestionagriculteur.token.PasswordResetToken.PasswordResetRequest;
import tn.esprit.picloundomicsgestionagriculteur.token.PasswordResetToken.PasswordTokenService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    private final PasswordTokenService passwordTokenService;

    @Autowired
    private final EmailServiceImpl emailService;



    private final UserServiceImp userServiceImp;

    private final LogoutService logoutService;

    private final UserRepository us;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));

    }

    @PutMapping("/block/{idUser}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> blockUser(@PathVariable Integer idUser) {
        service.blockUser(idUser);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/enlock/{idUser}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> enlockUser(@PathVariable Integer idUser) {
        service.enlockUser(idUser);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordResetRequest passwordResetRequest,
                                       final HttpServletRequest servletRequest)
            throws MessagingException, UnsupportedEncodingException {

        Optional<User> user = us.findByEmail(passwordResetRequest.getEmail());
        String passwordResetUrl = "";
        if (user.isPresent()) {
            String passwordResetToken = UUID.randomUUID().toString();
            userServiceImp.createPasswordResetTokenForUser(user.get(), passwordResetToken);
            passwordResetUrl = passwordResetEmailLink(user.get(),applicationUrl(servletRequest) , passwordResetToken);
        }
        return passwordResetUrl;
    }


    private String passwordResetEmailLink(User user, String applicationUrl,
                                          String passwordToken) throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/api/v1/auth/reset-password?token="+passwordToken;
        emailService.sendPasswordResetVerificationEmail(url);
        System.out.println("Click the link to reset your password :  {}" +url);
        return url;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordResetRequest passwordResetRequest,
                                @RequestParam("token") String token){
        String tokenVerificationResult = userServiceImp.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
            return "Invalid token password reset token";
        }
        Optional<User> theUser = Optional.ofNullable(userServiceImp.findUserByPasswordToken(token));
        if (theUser.isPresent()) {
            userServiceImp.resetPassword(theUser.get(), passwordResetRequest.getNewPassword());
            return "Password has been reset successfully";
        }
        return "Invalid password reset token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
    }


}

