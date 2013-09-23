package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.dto.ArbreDto;
import be.esi.alg2.gui.outils.MaJTable;
import be.esi.alg2.gui.outils.MaJTableInitialisationException;
import java.util.Collection;

/**
 * Composant permettant d'afficher la liste des arbres persistés recherchée.
 * @author Florian
 */
public class MaJTableArbre extends MaJTable<ArbreDto> {

    public MaJTableArbre() throws MaJTableInitialisationException {
        
    }
    
    public MaJTableArbre(Collection<ArbreDto> data) throws MaJTableInitialisationException {
        this();
        setData(data);
    }
    
    @Override
    protected void setTitres() {
        String[] t = {"Nom"};
        super.titres = t;
    }

    @Override
    protected void setMethods() {
        String[] t = {"getNom"};
        super.methodes = t;
    }

    @Override
    protected void setLargeurs() {
        int[] t = {0};
        largeurs = t;
    }

}
