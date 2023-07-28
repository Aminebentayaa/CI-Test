package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionTransformation;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table( name = "Equipe")
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;
        private String NomEquipe;

        private int numTel;
        private int nbEquipe;

        @Enumerated(EnumType.STRING)
        private Sepcialite sepcialite;
        private String Zone_dispo;
        @Temporal(TemporalType.DATE)
        private Date dateDebut;
        @Temporal(TemporalType.DATE)
        private Date dateFin;
        @Email
        private String email ;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

}

