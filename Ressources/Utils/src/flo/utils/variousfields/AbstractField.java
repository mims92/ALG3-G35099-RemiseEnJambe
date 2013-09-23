package flo.utils.variousfields;

import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Classe abstraite héritant d'un JTextField.
 * @author Florian Desneux
 */
public abstract class AbstractField<T> extends JTextField {
    protected Border defaut = getBorder();
    
    /**
     * Retourne la valeur du champs.
     * @return Retourne la valeur.
     */
    public abstract T getValue();
    
}
