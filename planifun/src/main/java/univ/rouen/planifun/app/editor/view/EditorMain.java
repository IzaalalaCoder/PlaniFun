package univ.rouen.planifun.app.editor.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import univ.rouen.planifun.app.editor.view.menu.MenuTask;

public class EditorMain extends JFrame {
    
    // ATTRIBUTES

    private ListTask leftComponent;
    private InformationTask mainComponent;
    private MenuTask menuTask;

    // CONSTRUCTORS

    public EditorMain() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(600, 500));
        this.createComponent();
        this.placeComponent();
        //this.createController();
    }

    // REQUESTS
    // COMMANDS
    // UTILS

    private void createComponent() {
        this.leftComponent = new ListTask();
        this.mainComponent = new InformationTask();
        this.menuTask = new MenuTask();
    }

    private void placeComponent() {
        this.add(leftComponent, BorderLayout.WEST);
        this.add(this.mainComponent, BorderLayout.CENTER);
        this.setJMenuBar(this.menuTask);
    }
}
