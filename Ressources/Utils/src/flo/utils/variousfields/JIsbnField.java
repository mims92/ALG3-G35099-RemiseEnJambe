package flo.utils.variousfields;


import be.esi.alg2.isbn.outils.Isbn;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;

/**
 * Défini un type de JTextField pour un Isbn : vérifie sa taille et sa validité.
 * @author Florian Desneux
 */
public class JIsbnField extends AbstractField<String> {

    /**
     * Construit un JIsbnField en lui ajoutant une règle de focus qui vérifie 
     * sa taille et sa validitié.
     */
    public JIsbnField() {
        this.setSize(200, 30);
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent me) {
                if (getText().trim().length() >= 1) {
                    if (!Isbn.valideIsbn10(getText()) && !Isbn.valideIsbn13(getText())) {
                        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                    } else {                        
                        setBorder(defaut);
                    }
                } else { 
                    setBorder(defaut);
                }
            }
        });
    }

    /**
     * Retourne la valeur du champs.
     * @return Retourne la valeur.
     */
    @Override
    public String getValue() {
        if (getText().trim().length() > 1) {
            return getText();
        } else {
            return null;
        }
    }
}
