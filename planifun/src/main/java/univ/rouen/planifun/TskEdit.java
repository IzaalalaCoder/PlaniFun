package univ.rouen.planifun;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import univ.rouen.planifun.app.editor.view.EditorMain;

public class TskEdit {
    public static void main(String[] args) {
        try {
            // Utiliser le look and feel Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // Créer et afficher votre fenêtre
            EditorMain editorMain = new EditorMain();
            editorMain.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}