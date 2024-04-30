package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Manage warning messages
 */
public class WarningPopUp {

    // STATIC REQUESTS

    /**
     * preventSaveAble : display warning message for erase todolist in new window
     * @return int
     */
    public static int preventSaveAble() {
        String message = "Etes-vous sûr de supprimer la liste de tâche en cours ?";

        return JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }

    /**
     * preventRemoveTask : display warning message for erase task in new window
     * @param name : name's task
     * @return int
     */
    public static int preventRemoveTask(String name) {
        String message = "Etes-vous sûr de supprimer la tâche " + name;

        return JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                new ImageIcon(ManageImage.PATH_ASSET + "information.png"));
    }
}