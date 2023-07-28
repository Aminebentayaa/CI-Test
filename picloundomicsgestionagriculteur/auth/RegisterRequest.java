package tn.esprit.picloundomicsgestionagriculteur.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterRequest {

    private String firstname;
    private String username;
    private int numTel;
    private String lastname;
    private String email;
    private String password;
    private String role;

}