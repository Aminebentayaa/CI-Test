package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionAgriculteur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionProduit.Produit;
import tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table( name = "Terrin")
public class Terrin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment
    private Integer idTerrin;
    private float surface;
    @Enumerated(EnumType.STRING)
    private SaisonRecolte saisonRecolte;
    private float EauCourante;
    @OneToOne
    @JoinColumn(name = "sol_id")
    private TypeSol sol;
    private int FrequenceArrosage;




    @Temporal(TemporalType.DATE)
    private Date dateObtentionTerrin;

    @ManyToOne
    @JoinColumn(name = "type_sol_id_sol")
    private TypeSol typeSol;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

    @OneToMany(mappedBy = "terrin", orphanRemoval = true)
    private Set<Produit> produits ;

}
