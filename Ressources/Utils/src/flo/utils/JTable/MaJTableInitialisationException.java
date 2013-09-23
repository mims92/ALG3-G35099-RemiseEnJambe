package flo.utils.JTable;

/**
 * Permet la gestion des erreurs spécifiques à MaJTable.
 * @author Florian Desneux
 */
public class MaJTableInitialisationException extends Exception {

    /**
     * Construit une exception par défaut.
     */
    public MaJTableInitialisationException() {
      
    }

    /**
     * Construit une exception personnalisée.
     * @param msg Le message de l'exception.
     */
    public MaJTableInitialisationException(String msg) {
        super(msg);
    }

}
