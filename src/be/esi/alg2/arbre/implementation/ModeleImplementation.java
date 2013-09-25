package be.esi.alg2.arbre.implementation;

import be.esi.alg2.arbre.mvc.ArbreModificationListener;
import be.esi.alg2.arbre.mvc.ArbreSelectionListener;
import be.esi.alg2.arbre.mvc.Modele;
import be.esi.alg2.arbre.mvc.NoeudBinaire;
import be.esi.alg2.arbre.mvc.ProfondeurMaximaleAtteinteException;
import be.esi.alg2.arbre.mvc.Valeur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alain
 */
public class ModeleImplementation implements Modele {

    private ArbreBinaireImplementation arbre;
    private NoeudBinaire sel;
    private List<ArbreModificationListener> arbreModlisteners;  //écouteurs des modifications de l'arbre
    private List<ArbreSelectionListener> arbreSellisteners;     //écouteurs des sélections de noeud de l'arbre
            
    public ModeleImplementation() {
        arbreModlisteners = new ArrayList<>();
        arbreSellisteners = new ArrayList<>();
        arbre = new ArbreBinaireImplementation();
    }

    /**
     * retourne la liste des noeuds de l'arbre dans l'ordre infixé.
     *
     * @return
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     */
    @Override
    public List<NoeudBinaire> getGRD() {
        ArrayList<NoeudBinaire> liste = new ArrayList<>();
        
        parcourInfixe(liste, this.arbre.getRacine());
        return liste;
    }
    
    private void parcourInfixe(ArrayList<NoeudBinaire> liste, NoeudBinaire nb) {
        if (nb != null) {
            parcourInfixe(liste, nb.getGauche());
            liste.add(nb);
            parcourInfixe(liste, nb.getDroite());
        }
    }

    /**
     * retourne la liste des noeuds de l'arbre dans l'ordre préfixé.
     *
     * @return
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     */
    @Override
    public List<NoeudBinaire> getRGD() {
        ArrayList<NoeudBinaire> liste = new ArrayList<>();
        
        parcoursPrefixe(liste, this.arbre.getRacine());
        return liste;
    }

    private void parcoursPrefixe(ArrayList<NoeudBinaire> liste, NoeudBinaire nb) {
        if (nb != null) {
            liste.add(nb);
            parcourInfixe(liste, nb.getGauche());
            parcourInfixe(liste, nb.getDroite());
        }
    }
    
    /**
     * retourne la liste des noeuds de l'arbre dans l'ordre postfixé.
     *
     * @return
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     */
    @Override
    public List<NoeudBinaire> getGDR() {
        ArrayList<NoeudBinaire> liste = new ArrayList<>();
        
        parcoursPostfixe(liste, this.arbre.getRacine());
        return liste;
    }
    
    private void parcoursPostfixe(ArrayList<NoeudBinaire> liste, NoeudBinaire nb) {
        if (nb != null) {
            parcourInfixe(liste, nb.getGauche());
            parcourInfixe(liste, nb.getDroite());
            liste.add(nb);
        }
    }

    /**
     * ajoute la valeur fournie à l'arbre
     *
     * @param valeur
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     * @throws ProfondeurMaximaleAtteinteException si l'ajout se fait au delà de
     * la profondeur maximale permise
     */
    @Override
    public void addValeur(Valeur valeur) throws ProfondeurMaximaleAtteinteException {
        int cle = valeur.getCle();
        if (arbre.getRacine() == null) {
            arbre.setRacine(new NoeudBinaireImplementation(valeur));
        } else {
            int i = 2;
            NoeudBinaireImplementation nb = arbre.getRacine();
            while ((nb.getGauche() != null && cle <= nb.getVal().getCle())
                    || nb.getDroite() != null && cle > nb.getVal().getCle()) {
                if (cle <= nb.getVal().getCle()) {
                    nb = nb.getGauche();
                } else {
                    nb = nb.getDroite();
                }
                i++;
            }
            if (i <= 5) {
                if (cle <= nb.getVal().getCle()) {
                    nb.setGauche(new NoeudBinaireImplementation(valeur));
                } else {
                    nb.setDroite(new NoeudBinaireImplementation(valeur));
                }
            } else {
                throw new ProfondeurMaximaleAtteinteException();
            }
        }

        refresh();
    }

    /**
     * retire à l'arbre le sous-arbre de racine noeud
     *
     * @param noeud racine du sous-arbre à ôter
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     */
    @Override
    public void oteSousArbre(NoeudBinaire noeud) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * retourne l'arbre repris par le modèle
     *
     * @return the arbre
     * @throws ArbreNonCreeException si l'arbre n'est pas créé
     */
    @Override
    public ArbreBinaireImplementation getArbre() {
        return arbre;
    }

    /**
     * retourne le NoeudBinaire sélectionné, null à défaut.
     *
     * @return the sel
     */
    @Override
    public NoeudBinaire getSel() {
        return sel;
    }

    /**
     * positionne le noeud sélectionné au noeud fourni
     *
     * @param sel the sel to set
     */
    @Override
    public void setSel(NoeudBinaire sel) {
        if (sel != this.sel) {
            this.sel = sel;
            refresh();
        }
    }

    @Override
    public void addModificationListener(ArbreModificationListener listener) {
        if (listener != null) {
            this.arbreModlisteners.add(listener);
        }
    }

    @Override
    public void removeModificationListener(ArbreModificationListener listener) {
        if (listener != null) {
            this.arbreModlisteners.remove(listener);
        }
    }

    @Override
    public void addSelectionListener(ArbreSelectionListener listener) {
        if (listener != null) {
            this.arbreSellisteners.add(listener);
        }
    }

    @Override
    public void removeSelectionListener(ArbreSelectionListener listener) {
        if (listener != null) {
            this.arbreSellisteners.remove(listener);
        }
    }

    /**
     * retire le noeud fourni de l'arbre courant A NE PAS DEVELOPPER
     *
     * @param noeud
     */
    @Override
    public void oteNoeud(NoeudBinaire noeud) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * crée un nouvela arbre vide dans le modèle
     */
    @Override
    public void nouvelArbre() {
        this.arbre = new ArbreBinaireImplementation();
    }

    /**
     * Allow views to be notified of changes.
     */
    private void refresh() {
        for (ArbreModificationListener mod : arbreModlisteners) {
            mod.notifyModArbre();
        }

        for (int i = 0; i < arbreSellisteners.size(); i++) {
            arbreSellisteners.get(i).notifyNewSelection(sel);
        }
    }
}
