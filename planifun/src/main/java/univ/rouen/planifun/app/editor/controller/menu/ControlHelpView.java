package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;

/**
 * Implements ActionListener to display help to use application
 */
public class ControlHelpView implements ActionListener {

    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String PATH_FILES = "src/main/resources/Aide.pdf";
        File output = new File(PATH_FILES);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(output);
        } catch (IOException e1) {
            ErrorPopUp.preventOpenOrParseFileError();
        }
    }
}