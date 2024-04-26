package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

public class CreateTask implements ActionListener {

    // ATTRIBUTES
    
    private EditorMain main;
    
    // CONSTRUCTORS
    
    public CreateTask(EditorMain main) {
        this.main = main;
    }

    // REQUESTS
    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String description = QuestionPopUp.inputString("La description de la tâche");
    }

    // UTILS
}
