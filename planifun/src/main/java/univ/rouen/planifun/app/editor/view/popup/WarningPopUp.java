package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class WarningPopUp {

    // STATIC REQUESTS

    public static int preventSaveAble() {

        String message = "Etes-vous sûr de supprimer la liste de tâche en cours ?";

        return JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(PopUpCommon.PATH_ASSET + "information.png"));
    }

    public static void preventCreateSetTask() {

        String message = "Vous deviez créer une liste de tâches avant de créer une tâche";

        JOptionPane.showConfirmDialog(null, message, "Attention ! ", 
            JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(PopUpCommon.PATH_ASSET + "information.png"));
    }

}
