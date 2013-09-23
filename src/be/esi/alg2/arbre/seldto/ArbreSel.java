package be.esi.alg2.arbre.seldto;

import java.util.Date;

/**
 * Classe d'instanciation de critères de sélection d'arbres
 *
 * <ul>Critères de sélection :
 * <li>debnom : début du nom</li>
 * </ul>
 * <br/>
 */
public class ArbreSel {
    private String nom;
    private Date ts;
    private int id;

    /**
     * Construit un ArbreSel avec son id.
     * @param id L'id de l'arbre.
     */
    public ArbreSel(int id){
        this.id=id;
    }

    /**
     * Construit un ArbreSel avec son nom.
     * @param nom Le nom de l'arbre.
     */
    public ArbreSel(String nom){
        this.nom = nom;
    }

    /**
     * @return Retourne le nom de l'arbre. 
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Retourne l'id de l'arbre. 
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return Retourne la date de création. 
     */
    public Date getTs() {
        return ts;
    }
}
