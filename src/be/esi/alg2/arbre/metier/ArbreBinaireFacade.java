package be.esi.alg2.arbre.metier;


import be.esi.alg2.arbre.db.ArbreDB;
import be.esi.alg2.arbre.db.ArbreDbException;
import be.esi.alg2.arbre.dto.ArbreCompletDto;
import be.esi.alg2.arbre.dto.ArbreDto;
import be.esi.alg2.arbre.implementation.ModeleImplementation;
import be.esi.alg2.arbre.mvc.Modele;
import java.util.Date;
import java.util.List;

/**
 * Classe utilitaire offrant les méthodes d'accès au modèle et les méthodes lièes à la persistance d'arbres
 * @author Alain & Florian Desneux
 */
public class ArbreBinaireFacade {

    public static Modele modele;

    /**
     * retourne la liste des ArbreDto des arbres persistés dont le nom commence par le contenu de debNom.
     * @param debNom Le début du nom de l'arbre cherché.
     * @return Retourne la liste des arbres dont le nom commence par celui passé en paramètre.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit. 
     */
    public static List<ArbreDto> getNomArbres(String debNom) throws ArbreDbException {
        return ArbreDB.listeNoms(debNom);
    }

    /**
     * Retourne le modèle courant, le crée s'il n'existe pas encore.
     * @return Le modèle.
     */
    public static Modele getModele() {
        if (modele == null) {
            modele = new ModeleImplementation();
        }
        return modele;
    }

    /**
     * persiste l'arbre fourni sous le nom spécifié.
     * Si le nom existe déjà, l'arbre persisté sera supprimé et le nouveau ajouté.
     * @param nom Le nom de l'arbre.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit. 
     */
    public static void persisteArbre(String nom) throws ArbreDbException {
            try {
                ArbreDB.saveArbre(new ArbreCompletDto(nom, new Date(), modele.getArbre()));
            } catch(ArbreDbException ex) {
                ArbreDB.supprimeArbre(nom);
                ArbreDB.saveArbre(new ArbreCompletDto(nom, new Date(), modele.getArbre()));
            }
    }

    /**
     * retourne ArbreCompletDto de l'arbre persisté dont le nom est fourni en paramètre.  
     * Si l'arbre n'existe pas, null est retourné.
     * @param nom Le nom de l'arbre à persister.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit. 
     */
    public static ArbreCompletDto chargeArbre(String nom) throws ArbreDbException {
        return ArbreDB.chargeArbre(nom);
    }
}
