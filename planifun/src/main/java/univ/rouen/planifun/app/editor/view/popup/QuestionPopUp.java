package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class QuestionPopUp {

    // STATIC REQUESTS
    
    public static String inputString(String message, String defaultValue) {
        return (String) JOptionPane.showInputDialog(null, message, "Saisie !",
            JOptionPane.QUESTION_MESSAGE, new ImageIcon(PopUpCommon.PATH_ASSET + "question.png"), 
            null, defaultValue
        );
    }

    public static String inputTypeTask() {
        String[] type = {"NORMAL", "BOOLEEN", "COMPLEXE"};

        return type[JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(PopUpCommon.PATH_ASSET + "question.png"), type, type[0])];

    }
    
    public static int inputChoicePriority() {
        String[] type = {"URGENT", "NORMAL", "SECONDAIRE"};

        return JOptionPane.showOptionDialog(null, "Veuillez indiquer la priorité de le tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(PopUpCommon.PATH_ASSET + "question.png"), type, type[0]);

    }
}
