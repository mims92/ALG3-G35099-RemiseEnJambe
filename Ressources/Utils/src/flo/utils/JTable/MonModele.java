package flo.utils.JTable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modèle de MaJTable.
 * @author Florian Desneux
 * @param <T> Le type de données contenue dans  MaJTable.
 */
public class MonModele<T> extends AbstractTableModel {

    private List<T> data;
    private String[] titresColonnes;
    private String[] methodNames;

    /**
     * Construit un modèle.
     * @param data Les données qui seront insérées dans le tableau.
     * @param titresColonnes Titres des colonnes.
     * @param methodNames Nom des méthodes.
     */
    public MonModele(Collection<T> data, String[] titresColonnes, String[] methodNames) {
        this.data = new ArrayList<T>(data);
        this.titresColonnes = titresColonnes;
        this.methodNames = methodNames;
    }

    /**
     * @return Retourne le nombre de lignes du tableau.
     */
    @Override
    public int getRowCount() {
        return (data == null ? 0 : data.size());
    }

    /**
     * @return Retourne le nombre de colonnes du tableau. 
     */
    public int getColumnCount() {
        return (titresColonnes == null ? 0 : titresColonnes.length);
    }

    /**
     * @param rowIndex Le numéro de la ligne.
     * @param columnIndex Le numéro de la colonne.
     * @return Retourne l'attribut de l'objet à la position reçue en paramètre 
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            T o = data.get(rowIndex);
            Method m = o.getClass().getMethod(methodNames[columnIndex]);
            return m.invoke(o);
        } catch (Exception ex) {
            // sans objet
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @param rowIndex Le numéro de la ligne.
     * @return Retourne l'objet à la ligne spécifiée.
     */
    public T getObject(int rowIndex) {
        return data.get(rowIndex);
    }

    @Override
    public String getColumnName(int rowIndex) {
        return titresColonnes[rowIndex];
    }

}
