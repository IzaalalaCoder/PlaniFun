package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.util.ManageImage;
import java.util.Calendar;

/**
 * Manage input messages
 */
public class QuestionPopUp {

    // STATIC REQUESTS

    /**
     * inputString : display input text in new window
     * @param message : message
     * @param defaultValue : default
     * @return String
     */
    public static String inputString(String message, String defaultValue) {
        return (String) JOptionPane.showInputDialog(null, message, "Saisie !",
            JOptionPane.QUESTION_MESSAGE, new ImageIcon(ManageImage.PATH_ASSET + "question.png"), 
            null, defaultValue
        );
    }

    /**
     * inputTypeTask : display input type task in new window
     * @param c : default calendar
     * @return Task
     */
    public static Task inputTypeTask(Calendar c) {
        String[] type = {"NORMAL", "BOOLEEN", "COMPLEXE"};
        
        int result = JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(ManageImage.PATH_ASSET + "question.png"), type, type[0]);

        return result == -1 ? null : switch (type[result]) {
            case "NORMAL" -> new NormalTask(c);
            case "BOOLEEN" -> new BooleanTask(c);
            case "COMPLEXE" -> new ComplexTask(c);
            default -> null;
        };
    }

    /**
     * inputChoicePriority : display input priority for task in new window
     * @return int
     */
    public static int inputChoicePriority() {
        String[] type = {"URGENT", "NORMAL", "SECONDAIRE"};

        return JOptionPane.showOptionDialog(null, "Veuillez indiquer la priorité de le tâche", 
            "Choix du type de tâche", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            new ImageIcon(ManageImage.PATH_ASSET + "question.png"), type, type[0]);
    }
}