package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.dto.ArbreDto;
import be.esi.alg2.arbre.metier.ArbreBinaireFacade;
import be.esi.alg2.arbre.seldto.ArbreSel;
import be.esi.alg2.gui.outils.JDRechGenerique;
import java.util.Collection;

/**
 * Fenêtre permettant de rechercher et de visualiser les arbres recherchés.
 * @author Florian
 */
public class JDRechercheArbre extends JDRechGenerique<ArbreSel, ArbreDto>{
    
    public JDRechercheArbre(java.awt.Frame parent, boolean modal, String titre, JPRechercheArbre rech, MaJTableArbre table) {
        super(parent, modal, titre, rech, table);
    }
    
    @Override
    protected Collection<ArbreDto> recherche(ArbreSel s) throws Exception {
        return ArbreBinaireFacade.getNomArbres(s.getNom());
    }

    /*public static void main(String[] args) throws MaJTableInitialisationException{
        new JDRechercheArbre(null, true, "Sélection d'arbres", new JPRechercheArbre(), new MaJTableArbre()).setVisible(true);
    }*/
}
