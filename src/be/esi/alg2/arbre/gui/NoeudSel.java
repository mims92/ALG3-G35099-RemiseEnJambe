package be.esi.alg2.arbre.gui;

import be.esi.alg2.arbre.mvc.ArbreSelectionListener;
import be.esi.alg2.arbre.mvc.NoeudBinaire;

/**
 *
 * @author Florian
 */
public class NoeudSel extends javax.swing.JDialog implements ArbreSelectionListener {

    /**
     * Creates new form NoeudSel
     */
    public NoeudSel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Permet d'afficher le panel avec les infos mises à jour.
     * @param key Le clé du noeud.
     * @param lib Le libellé du noeud.
     */
    private void showPanel(String key, String lib) {
        this.key.setText(key);
        this.lib.setText(lib);
        
        this.setTitle("Noeud sélectionné");
        this.vuePane.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vuePane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        key = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lib = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Clé :");

        key.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel3.setText("Libellé :");

        javax.swing.GroupLayout vuePaneLayout = new javax.swing.GroupLayout(vuePane);
        vuePane.setLayout(vuePaneLayout);
        vuePaneLayout.setHorizontalGroup(
            vuePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vuePaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(vuePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vuePaneLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lib, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                    .addGroup(vuePaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(key)))
                .addContainerGap())
        );
        vuePaneLayout.setVerticalGroup(
            vuePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vuePaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(vuePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(key))
                .addGap(18, 18, 18)
                .addGroup(vuePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lib))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vuePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
            java.util.logging.Logger.getLogger(NoeudSel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NoeudSel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NoeudSel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NoeudSel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NoeudSel dialog = new NoeudSel(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel key;
    private javax.swing.JLabel lib;
    private javax.swing.JPanel vuePane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyNewSelection(NoeudBinaire nb) {
        if (nb == null) {
            this.setTitle("Aucun noeud sélectionné");
            this.vuePane.setVisible(false);
        } else {
            this.showPanel(nb.getVal().getCle().toString(), nb.getVal().getLibelle());
        }
    }
}