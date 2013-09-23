package be.esi.alg2.arbre.implementation;

import be.esi.alg2.arbre.mvc.ArbreBinaire;



/**
 * A ne pas modifier
 */
public class ArbreBinaireImplementation implements ArbreBinaire {
    private NoeudBinaireImplementation racine;
    
    /**
     * Permet d'ajouter une racine.
     * @param noeud Le noeud racine.
     */
    void setRacine(NoeudBinaireImplementation noeud){
        racine=noeud;
    }
   
    @Override
    public NoeudBinaireImplementation getRacine(){
        return racine;
    }
        
    
}
