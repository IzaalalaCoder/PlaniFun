package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

public class ControlQuit implements ActionListener {

    // ATTRIBUTES 

    private EditorMain main;

    // CONSTRUCTOR

    public ControlQuit(EditorMain main) {
        this.main = main;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.main.getModel() != null) {
            if (WarningPopUp.preventSaveAble() != JOptionPane.YES_OPTION) {
                return;
            }
        }
        this.main.dispose();
    }
    
}
