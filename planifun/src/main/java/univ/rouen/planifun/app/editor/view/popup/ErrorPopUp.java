package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Manage error messages
 */
public class ErrorPopUp {

    // STATIC COMMANDS

    /**
     * preventOpenOrParseFileError : display open or parse file error message in new window
     */
    public static void preventOpenOrParseFileError() {

        String message = "Le fichier n'a pas pu être ouvert ou a été corrompu";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "error.png"));
    }

    /**
     * preventMoveFileError : display move file error message in new window
     */
    public static void preventMoveFileError() {

        String message = "Le fichier n'a pas pu être déplacé";

        JOptionPane.showMessageDialog(null, message, "Erreur"
                , JOptionPane.ERROR_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "error.png"));
    }

    /**
     * preventUnknownType : display type's task error message in new window
     */
    public static void preventUnknownType() {

        String message = "La création de ce type de tâche est inexistant";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "error.png"));
    }

    /**
     * preventCreate : display type's task error message in new window
     * @param message : warning message
     */
    public static void preventCreate(String message) {
        JOptionPane.showConfirmDialog(null, message, "Attention ! ",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }
}