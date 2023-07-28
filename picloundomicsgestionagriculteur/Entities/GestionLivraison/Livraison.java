package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionLivraison;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit.Commande;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit.StatusPaiement;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table( name = "Livraison")
public class Livraison  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivraison;

    private float  prixLivraison;
    private int numeroRegionDest;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    private StatusPaiement statusPaiement;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;


    @OneToMany(mappedBy = "livraison", orphanRemoval = true)
    private Set<Commande> commandes ;

}
