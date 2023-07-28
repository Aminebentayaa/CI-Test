package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionAgriculteur.Terrin;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionLivraison.Livraison;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit.Commande;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit.Produit;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionReclamation.Reclamation;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionTransformation.Equipe;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionTransformation.Pesticide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table( name = "User")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotNull(message = "Le nom d'utilisateur ne peut pas être nul")
    private String username;

    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String firstname;

    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String lastname;
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Le mot de passe doit contenir au moins une lettre et un chiffre")
    private String password;


    private int numTel;

    private String confirmationToken;

    @NotNull
    private Boolean locked = false;
    private Boolean enabled = false;

    @Email(message = "L'adresse email doit être valide")
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;


    @Transient
    @JsonIgnore
    private String confirmPassword;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Pesticide> pesticides ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Equipe> equipes ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Livraison> livraisons ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Reclamation> reclamations ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Terrin> terrins ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Produit> produits ;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Commande> commandes ;

    @Override
    @JsonSerialize(using = UserAuthoritySerializer.class)
    @JsonDeserialize(using = UserAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}

