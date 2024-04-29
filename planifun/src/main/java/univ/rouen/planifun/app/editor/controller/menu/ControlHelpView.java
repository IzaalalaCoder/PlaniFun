package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;

public class ControlHelpView implements ActionListener {

    // CONSTANTS

    private final String PATH_FILES = "planifun/src/main/resources/Aide.pdf";
    
    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File output = new File(PATH_FILES); 
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(output);
        } catch (IOException e1) {
            ErrorPopUp.preventOpenOrParseFileError();
        }
    }
    
}
