package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

public class QuestionPopUp {

    // STATIC REQUESTS
    
    public static String inputString(String message, String defaultValue) {
        return (String) JOptionPane.showInputDialog(null, message, "Saisie !",
            JOptionPane.QUESTION_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "question.png"), 
            null, defaultValue
        );
    }

    public static String inputTypeTask() {
        String[] type = {"NORMAL", "BOOLEEN", "COMPLEXE"};
        
        int result = JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(ManageImage.PATH_ASSET + "question.png"), type, type[0]);

        return result == -1 ? null : type[result];
    }
    
    public static int inputChoicePriority() {
        String[] type = {"URGENT", "NORMAL", "SECONDAIRE"};

        return JOptionPane.showOptionDialog(null, "Veuillez indiquer la priorité de le tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(ManageImage.PATH_ASSET + "question.png"), type, type[0]);

    }
}
