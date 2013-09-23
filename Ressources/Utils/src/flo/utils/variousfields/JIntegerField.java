package flo.utils.variousfields;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;

/**
 * Component that allows the editing of a single line and check if the text
 * is a number of type <b>int</b>.
 * @author Florian Desneux
 */
public class JIntegerField extends AbstractField<Integer> {

    /**
     * Construct an Integer field.
     */
    public JIntegerField() {
        this.setSize(100, 30);
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent me) {
                if (getText().trim().length() >= 1) {
                    try {
                        Integer.parseInt(getText().trim());
                        setBorder(defaut);
                        //fix bug of size
                        setSize(100, 30);
                    } catch (Exception execption) {
                        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                        //fix bug of size
                        setSize(100, 30);
                    }
                } else {
                    setBorder(defaut);
                    //fix bug of size
                    setSize(100, 30);
                }
            }
        });

    }

    @Override
    public Integer getValue() throws NumberFormatException {
        if (getText().trim().length() >= 1) {
            return Integer.parseInt(getText().trim());
        } else {
            return null;
        }
    }
}
