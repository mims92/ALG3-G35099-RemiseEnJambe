package be.esi.alg2.arbre.metier;

/**
 * Exception lancée par les méthodes façade en cas d'erreur de type:
 * <ul>
 * <li>Persistance</li>
 * <li>Métier</li>
 * <li>...</li>
 * </ul>
 * @author Alain
 */
public class ArbreMetierException extends Exception {

    /**
     * Creates a new instance of
     * <code>ArbreMetierException</code> without detail message.
     */
    public ArbreMetierException() {
    }

    /**
     * Constructs an instance of
     * <code>ArbreMetierException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ArbreMetierException(String msg) {
        super(msg);
    }
}
