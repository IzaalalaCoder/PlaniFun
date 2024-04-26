package univ.rouen.planifun.app.editor.view.popup;

import javax.swing.JOptionPane;

public class QuestionPopUp {
    
    public static String inputString(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static int inputTypeTask() {
        String[] type = {"NORMAL", "BOOLEEN", "COMPLEXE"};

        return JOptionPane

    }
}
