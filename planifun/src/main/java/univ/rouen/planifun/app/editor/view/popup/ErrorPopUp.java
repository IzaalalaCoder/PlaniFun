package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.JOptionPane;

public class ErrorPopUp {

    // STATIC COMMANDS

    public static void preventOpenOrParseFileError() {

        String message = "Le fichier n'a pas pu être ouvert ou a été corrompu";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE);
    }

    public static void preventUnknowTyoe() {

        String message = "La création de ce type de tâche est inexistant";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE);
    }
}
