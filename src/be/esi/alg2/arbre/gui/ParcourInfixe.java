package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.ArbreModificationListener;
import be.esi.alg2.arbre.mvc.ArbreSelectionListener;
import be.esi.alg2.arbre.mvc.Modele;
import be.esi.alg2.arbre.mvc.NoeudBinaire;
import be.esi.alg2.arbre.mvc.Valeur;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Florian
 */
public class ParcourInfixe extends javax.swing.JDialog implements ArbreModificationListener, 
        ArbreSelectionListener  {

    private Modele modele;
    private JList list;
    private DefaultListModel<Valeur> listModel;
    private JScrollPane listScrollPane;

    /**
     * Creates new form ParcourInfixe
     */
    public ParcourInfixe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        listModel = new DefaultListModel<Valeur>();
        list = new JList();

        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(list.getSelectedIndex());
            }
        });
        
        //NoeudBinaire nb = (NoeudBinaire) listModel.get(list.getSelectedIndex());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParcourInfixe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParcourInfixe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParcourInfixe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParcourInfixe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ParcourInfixe dialog = new ParcourInfixe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyModArbre() {
        setList();
    }

    @Override
    public void notifyNewSelection(NoeudBinaire nb) {
        if (nb != null) {
            list.setSelectedValue(nb.getVal(), true);
        }
    }

    private void setList() {
        if (listScrollPane != null) {
            remove(listScrollPane);
        }

        listModel.clear();

        for (NoeudBinaire bin : modele.getGRD()) {
            listModel.addElement(bin.getVal());

        }

        list = new JList(listModel);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);

        listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);

        validate();

        pack();
    }
}
