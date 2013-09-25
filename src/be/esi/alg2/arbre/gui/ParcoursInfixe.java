/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.NoeudBinaire;


/**
 *
 * @author Florian Desneux
 */
public class ParcoursInfixe extends Parcours {

    public ParcoursInfixe() {
        super("Parcours infixe");
    }
    
    @Override
    void addElem() {
        for (NoeudBinaire bin : modele.getGRD()) {
            listModel.addElement(bin);

         }
    } 
}
