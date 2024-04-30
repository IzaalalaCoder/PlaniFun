package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

/**
 * Implements ActionListener to create new todolist
 */
public class ControlCreateSetTask implements ActionListener {

    // ATTRIBUTES
    
    private final EditorMain main;

    // CONSTRUCTORS

    public ControlCreateSetTask(EditorMain main) {
        this.main = main;
    }
    
    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.main.getModel() != null) {
            if (WarningPopUp.preventSaveAble() != JOptionPane.OK_OPTION) {
                return;
            }
        }

        String name = QuestionPopUp.inputString("Quel sera le nom de la liste de tâche", 
            "Liste de tâche");
        if (name == null || name.isEmpty()) {
            return;
        }
 
        SetTask model = new SetTask();
        model.setName(name);
        main.setModel(model);
    }
}