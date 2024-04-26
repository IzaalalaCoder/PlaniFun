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
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

public class ControlSaveSetTask implements ActionListener {

    // ATTRIBUTES
    
    private EditorMain main;

    // CONSTRUCTORS

    public ControlSaveSetTask(EditorMain main) {
        this.main = main;
    }
    
    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (main.getModel() == null) {
            WarningPopUp.preventCreateSetTask();
            return;
        }

        WritingXML writer = new WritingXML(main.getModel());
        try {
            writer.writeXMLFile();
        } catch (ParserConfigurationException e1) {} 
        catch (TransformerException e1) {}

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
            } catch (IOException e1) {}
        }
    }
    
}
