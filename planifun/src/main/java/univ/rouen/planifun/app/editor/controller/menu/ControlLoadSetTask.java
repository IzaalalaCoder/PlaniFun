package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import univ.rouen.planifun.app.builder.xml.read.ReadingXML;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

/**
 * Implements ActionListener to manage load todolist
 */
public class ControlLoadSetTask implements ActionListener {

    // ATTRIBUTES

    private final EditorMain main;

    // CONSTRUCTORS

    public ControlLoadSetTask(EditorMain main) {
        this.main = main;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (main.getModel() != null) {
            if (WarningPopUp.preventSaveAble() != JOptionPane.YES_OPTION) {
                return;
            }
        } 

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Sélectionner une sauvegarde");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
        fileChooser.addChoosableFileFilter(filter);

        int response = fileChooser.showOpenDialog(main);
        File f = fileChooser.getSelectedFile();
        if (response == JFileChooser.APPROVE_OPTION && f != null) {
            try {
                openSetTask(f);
            } catch (IOException e1) {
               ErrorPopUp.preventOpenOrParseFileError();
            }
        }
    }

    // UTILS

    /**
     * openSetTask : retrieve todolist in selected file
     * @param file : selected file
     * @throws IOException : error on open file
     */
    private void openSetTask(File file) throws IOException {
        ReadingXML readerXML = new ReadingXML(file);
        readerXML.readXMLFile();
        this.main.setModel(readerXML.getSetTaskInFile());
    }
}