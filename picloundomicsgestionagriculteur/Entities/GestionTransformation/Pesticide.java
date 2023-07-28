package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionTransformation;

import jakarta.persistence.*;
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
@Table( name = "Pesticide")
public class Pesticide implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesticide;

    @Enumerated(EnumType.STRING)
    private TypePesticide typePesticide;
    private Boolean choixEquipe;

    @Temporal(TemporalType.DATE)
    private Date dateLivraisonPesticide;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

}
