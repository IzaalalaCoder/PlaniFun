package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

public class ControlCreateSetTask implements ActionListener {

    // ATTRIBUTES
    
    private EditorMain main;

    // CONSTRUCTORS

    public ControlCreateSetTask(EditorMain main) {
        this.main = main;
    }
    
    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = QuestionPopUp.inputString("Quel sera le nom de la liste de tâche", "Liste de tâche");
        if (name == null) {
            return;
        }
 
        SetTask model = new SetTask();
        model.setName(name);
        main.setModel(model);
    }
    
}
