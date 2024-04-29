package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

public class WarningPopUp {

    // STATIC REQUESTS

    public static int preventSaveAble() {

        String message = "Etes-vous sûr de supprimer la liste de tâche en cours ?";

        return JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }

    public static int preventRemoveTask(String name) {

        String message = "Etes-vous sûr de supprimer la tâche " + name;

        return JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }

    public static void preventCreate(String message) {

        JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }

}
