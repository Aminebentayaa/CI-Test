package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionAgriculteur.Terrin;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table( name = "Produit")


public class Produit implements Serializable  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)//auto increment
     private Integer idproduit;
    private String nomProduit;
    @Temporal(TemporalType.DATE)
    private Date dataCreation;
    private float prixProduit ;
    @Temporal(TemporalType.DATE)
    private Date dateValidite;
    private Boolean validite;
    @Enumerated(EnumType.STRING)
    private CategorieProduit categorieProduit;
    private float quantite;

   private  int qtitDechet;
    private int maxDechet;


    @ManyToOne
    @JoinColumn(name = "terrin_id_terrin")
    private Terrin terrin;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

    @ManyToMany
    @JoinTable(name = "produit_commandes",
            joinColumns = @JoinColumn(name = "produit_idproduit"),
            inverseJoinColumns = @JoinColumn(name = "commandes_id_command"))
    private Set<Commande> commandes;

}
