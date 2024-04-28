package univ.rouen.planifun;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import univ.rouen.planifun.app.editor.view.EditorMain;

public class TskEdit {

    // ENTERED POINT 

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            EditorMain editorMain = new EditorMain();
            editorMain.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}