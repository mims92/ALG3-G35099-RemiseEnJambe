package flo.utils.variousfields;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Classe permettant d'afficher un message de description dans une JTextField.
 * Redéfini le Placeholder.
 * @author Florian Desneux
 */
public class JTextFieldPlaceholder extends AbstractField<String> {
    
    private String placeHolder;
    
    /**
     * Construit un JTextFieldPlaceholder sans message.
     */
    public JTextFieldPlaceholder() {
        this.setSize(200, 30);
    }
    
    /**
     * Construit une JTextFieldPlaceholder avec un message par défaut
     * @param s Le message à afficher.
     */
    public JTextFieldPlaceholder(String s) {
        this.placeHolder = s;
        this.setText(this.placeHolder);
        this.setForeground(Color.LIGHT_GRAY);
        
        this.addFocusListener(new FocusAdapter() {
            
            //Quand on gagne le focus, le couleur du texte change
            @Override
            public void focusGained(FocusEvent e) {
                if (JTextFieldPlaceholder.this.getText().equals(JTextFieldPlaceholder.this.placeHolder))
                JTextFieldPlaceholder.this.setText("");
                JTextFieldPlaceholder.this.setForeground(Color.BLACK);
            }
            
            //Quand on le perd, la couleur revient à celle par défaut
            @Override
            public void focusLost(FocusEvent e) {
                if (JTextFieldPlaceholder.this.getText().equals("")) {
                    JTextFieldPlaceholder.this.setText(JTextFieldPlaceholder.this.placeHolder);
                    JTextFieldPlaceholder.this.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }   
    
    /**
     * @return Retourne vrai si la couleur en premier plan n'est pas grise.
     */
    @Override
    public boolean isForegroundSet() {
        return (this.getForeground() == Color.LIGHT_GRAY) ? true : false;
    }   
    
    /**
     * 
     * @return La valeur du champs. 
     */
    @Override
    public String getValue() {
        if (!(isForegroundSet()) && (this.getText().trim().length() >= 1)) {
            return getText().trim();
        } else {
            return null;
        }
    }
}
