package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.NoeudBinaire;

/**
 *
 * @author Florian Desneux
 */
public class ParcoursPrefixe extends Parcours {

    public ParcoursPrefixe() {
        super("Parcours prefixe");
    }
    
    @Override
    void addElem() {
        for (NoeudBinaire bin : modele.getRGD()) {
            listModel.addElement(bin);
        }
    }
    
}
