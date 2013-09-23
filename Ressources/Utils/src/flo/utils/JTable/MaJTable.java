package flo.utils.JTable;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe abstraite de facilitation de définition de JTable
 * @author ADT
 */
public abstract class MaJTable<T> extends JPanel {

    public static String PROPERTY_EVENT_SELECT = "select";
    public static String PROPERTY_EVENT_DBCLICK = "dbclick";
    private JTable maTable;
    private MonModele<T> monModele;
    private T objSelected = null;
    protected Collection<T> data;
    protected String[] titres;
    protected String[] methodes;
    protected int[] largeurs;

    /**
     * Construit une JTable.
     * @throws MaJTableInitialisationException Exception lancée lorsque la table
     * n'est pas définie correctement.
     */
    protected MaJTable() throws MaJTableInitialisationException {
        setLayout(new BorderLayout());
        maTable = new JTable();
        gereEvtSelections();
        add(new JScrollPane(maTable));
        init();
        maTable.setDragEnabled(false);
        maTable.getTableHeader().setReorderingAllowed(false);
        maTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Initialise la table.
     * Place les titre, la largeur, et les méthodes de chaque colonne.
     * @throws MaJTableInitialisationException Exception lancée lorsque la table
     * n'est pas définie correctement (le nombre de colonnes, de méthodes et de largeur
     * de colonnes différent).
     */
    private void init() throws MaJTableInitialisationException {
        setTitres();
        setMethods();
        setLargeurs();
        if (titres == null || methodes == null || largeurs == null) {
            throw new MaJTableInitialisationException("Définition incomplète de MaJTable!");
        }
        if (titres.length != methodes.length || methodes.length != largeurs.length) {
            throw new MaJTableInitialisationException("Définition incohérente de MaJTable!");
        }
    }

    /** positionne les titres de chacune des colonnes dans titres */
    protected abstract void setTitres();

    /** positionne les noms des méthodes  dans methodes */
    protected abstract void setMethods();

    /** positionne les largeurs de colonnes dans largeurs */
    protected abstract void setLargeurs();

    /**
     * Défini le modèle du tableau.
     * @param monModele Le nouveau modèle.
     */
    protected void setModel(MonModele<T> monModele) {
        this.monModele = monModele;
        maTable.setModel(monModele);
    }

    /** adapte les largeurs des colonnes aux valeurs de largeurs */
    public void setColumnWidth(int[] largeurs) {
        if (largeurs != null) {
            for (int i = 0; i < largeurs.length && i < maTable.getColumnCount(); i++) {
                maTable.getColumn(maTable.getColumnName(i)).setPreferredWidth(largeurs[i]);
            }
        }
    }

    /** retourne le nombre de lignes de la JTable */
    public int getRowCount() {
        return maTable.getRowCount();
    }

    /** retourne l'index de l'éléments sélectionné ou -1 à défaut */
    public int getSelectedRow() {
        return maTable.getSelectedRow();
    }

    /** retourne l'objet T sélectionné ou null à défaut*/
    public T getSelectedObject() {
        if (maTable.getSelectedRow() < 0 || monModele == null) {
            return null;
        } else {
            return monModele.getObject(maTable.getSelectedRow());
        }
    }

    private void gereEvtSelections() {
        maTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //ne prend pas en compte l'évènement de préparation à la sélection
                if (!e.getValueIsAdjusting()) {

                    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                    T old = objSelected;
                    if (lsm.isSelectionEmpty()) {
                        objSelected = null;
                    } else {
                        objSelected = getSelectedObject();
                    }
                    firePropertyChange(PROPERTY_EVENT_SELECT, old, objSelected);
                }
            }
        });

        maTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    firePropertyChange(PROPERTY_EVENT_DBCLICK, null, objSelected);
                }
            }
        });
    }

    /** Positionne le modèle à la collection fournie */
    public void setData(Collection<T> col) {
        this.data = col;
        MonModele<T> modele = new MonModele<T>(col, titres, methodes);
        setModel(modele);
        setColumnWidth(largeurs);
    }
}
