package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.JOptionPane;

public class QuestionPopUp {
    
    public static String inputString(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static String inputTypeTask() {
        String[] type = {"NORMAL", "BOOLEEN", "COMPLEXE"};

        return type[JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, type, type[0])];

    }
}
