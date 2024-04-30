package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import univ.rouen.planifun.app.builder.xml.write.WritingXML;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;

/**
 * Implements ActionListener to manage save todolist
 */
public class ControlSaveSetTask implements ActionListener {

    // ATTRIBUTES
    
    private final EditorMain main;

    // CONSTRUCTORS

    public ControlSaveSetTask(EditorMain main) {
        this.main = main;
    }
    
    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (main.getModel() == null) {
            ErrorPopUp.preventCreate("Vous deviez créer une liste de tâches avant de sauvegarder celle-ci");
            return;
        }

        WritingXML writer = new WritingXML(main.getModel());
        try {
            writer.writeXMLFile();
        } catch (ParserConfigurationException | TransformerException e1) {
            ErrorPopUp.preventOpenOrParseFileError();
        }

        File f = writer.getGeneratedFile();
        JFileChooser chooserDirectory = new JFileChooser();
        chooserDirectory.setCurrentDirectory(new File("."));
        chooserDirectory.setDialogTitle("Choisir l'emplacement");
        chooserDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserDirectory.setAcceptAllFileFilterUsed(false);

        int result = chooserDirectory.showOpenDialog(main);
        if (result == JFileChooser.APPROVE_OPTION) { 
            try {
                Files.move(f.toPath(), Paths.get(chooserDirectory.getSelectedFile() + "/" + f.getName()));
            } catch (IOException e1) {
                ErrorPopUp.preventMoveFileError();
            }
        }
    }
}