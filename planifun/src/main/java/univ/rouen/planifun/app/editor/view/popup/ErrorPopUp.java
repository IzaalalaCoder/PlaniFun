package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

public class ErrorPopUp {

    // STATIC COMMANDS

    public static void preventOpenOrParseFileError() {

        String message = "Le fichier n'a pas pu être ouvert ou a été corrompu";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "error.png"));
    }

    public static void preventUnknowType() {

        String message = "La création de ce type de tâche est inexistant";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "error.png"));
    }
}
