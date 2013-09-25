package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.ArbreModificationListener;
import be.esi.alg2.arbre.mvc.ArbreSelectionListener;
import be.esi.alg2.arbre.mvc.Modele;
import be.esi.alg2.arbre.mvc.NoeudBinaire;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Florian Desneux
 */
public abstract class Parcours extends javax.swing.JDialog implements ArbreModificationListener,
        ArbreSelectionListener {

    protected Modele modele;
    private JList list;
    protected DefaultListModel<NoeudBinaire> listModel;
    private JScrollPane listScrollPane;

    /**
     * Creates new form ParcoursInfixe
     */
    public Parcours(String title) {
        listModel = new DefaultListModel<>();
        list = new JList();

        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    modele.setSel(listModel.get(list.getSelectedIndex()));
                }
            }
        });
        
        setLayout(new BorderLayout());
    }

    /**
     * Ajoute le modèle.
     *
     * @param modele Le nouveau modèle.
     */
    public void setModele(Modele modele) {
        if (modele != null) {
            this.modele = modele;

            modele.addModificationListener(this);
            modele.addSelectionListener(this);
        }
    }

    @Override
    public void notifyModArbre() {
        setList();
    }

    @Override
    public void notifyNewSelection(NoeudBinaire nb) {
         if (list.getSelectedIndex() != -1) {
            if ((nb != null)) {
                list.setSelectedValue(nb, true);
            }
         }
    }

    /**
     * Construit la liste.
     */
    private void setList() {
        if (listScrollPane != null) {
            remove(listScrollPane);
        }

        listModel.clear();

        addElem();

        list.removeAll();
        
        list.setModel(listModel);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);

        listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);

        validate();
        pack();
    }

    /**
     * ajoute les objets (NoeudBinaire) à la liste.
     */
    abstract void addElem();
}
