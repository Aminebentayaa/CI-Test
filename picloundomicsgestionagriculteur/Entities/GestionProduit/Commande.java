package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionLivraison.Livraison;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table( name = "Commande")

public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment
    private Integer idCommand;
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;
    @Enumerated(EnumType.STRING)
    private StatusPaiement statusPaiement;

    @ManyToOne
    @JoinColumn(name = "livraison_id_livraison")
    private Livraison livraison;

    @ManyToMany(mappedBy = "commandes")
    private Set<Produit> produits = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

}
