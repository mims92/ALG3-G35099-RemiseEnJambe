package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.NoeudBinaire;

/**
 *
 * @author Florian Desneux
 */
public class ParcoursPostfixe extends Parcours {

    public ParcoursPostfixe() {
        super("Parcours postfixé");
    }
    
    @Override
    void addElem() {
        for (NoeudBinaire bin : modele.getGDR()) {
            listModel.addElement(bin);
        }
    }
    
}
