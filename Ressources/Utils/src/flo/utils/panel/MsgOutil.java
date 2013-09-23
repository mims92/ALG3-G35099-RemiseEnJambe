package flo.utils.panel;

import javax.swing.JOptionPane;

/**
 * Classe permettant d'afficher des messages d'erreurs ainsi que des messages
 * d'information, de confirmation (à la suite d'une action effectuée)
 * @author Florian Desneux
 */
public class MsgOutil {
    /**
     * Affiche un message d'information.
     * @param title Le titre de la fenêtre.
     * @param text Le texte à afficher.
     */
    public static void showInfo(String title, String text) {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Affiche un message d'erreur.
     * @param e L'exception lancée.
     * @param messageAdditionnel Le message additionnel.
     */
    public static void showErreur(Exception e, String messageAdditionnel) {
        JOptionPane.showMessageDialog(null, messageAdditionnel + e.getMessage(),
                e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Affiche un message d'erreur.
     * @param title Le titre de la fenêtre.
     * @param message Le message.
     */
    public static void showErreur(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /*
     * Message de débogage. 
     * @param s Le titre de la fenêtre.
     * @param messageAdditionnel Le message additionnel.
     */
    public static void debog(String s, String messageAdditionnel) {
        JOptionPane.showMessageDialog(null, messageAdditionnel , s,  JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Fenêtre de confirmation d'évènement.
     * @param title Le titre de la fenêtre
     * @param messageAdditionnel Le message.
     * @return Retourne vrai si l'utilisateur a confirmé, faux dans le cas contraire.
     */
    public static int confirmation(String title, String messageAdditionnel) {
        return JOptionPane.showConfirmDialog(null, messageAdditionnel, title, JOptionPane.YES_NO_OPTION);
    }
}
