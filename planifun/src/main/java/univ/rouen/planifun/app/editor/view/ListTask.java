package univ.rouen.planifun.app.editor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import univ.rouen.planifun.app.editor.controller.list.ReactionItemTask;
import univ.rouen.planifun.app.editor.controller.list.RemoveTask;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ListTask extends JPanel {

    // CONSTANTS

    private final Color COLOR_NULL = Color.darkGray;
    private final Color COLOR_NOT_NULL = Color.white;
    private final String PATH_ASSET = "planifun/src/main/resources/assets/";

    // ATTRIBUTES

    private SetTask model;
    private Map<Task, JPanel> panels;
    private Map<JPanel, JPanel> subPanel;
    private Map<Task, JButton> removeButton;

    // CONSTRUCTORS

    public ListTask() {
        this.panels = new HashMap<>();
        this.removeButton = new HashMap<>();
        this.subPanel = new HashMap<>();
        this.model = null;
        this.initializeComponent();
    }

    // REQUESTS
    // COMMANDS

    public void setModel(SetTask model) {
        this.setBackground(COLOR_NOT_NULL);
        if (this.model != null) {
            this.removeAllControllers();
            this.removeAllComponents();
        }
        
        this.model = model;

        this.createController();
        this.createComponents();
    }

    // UTILS

    private void createController() {
        this.model.addPropertyChangeListener(SetTask.PROP_ADD_TASKS, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(true, (Task) evt.getNewValue());
            }
        
        });

        this.model.addPropertyChangeListener(SetTask.PROP_REMOVE_TASKS, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(false, (Task) evt.getNewValue());
            }
        
        });
    }

    private void updateListAboutTask(boolean added, Task t) {
        if (added) {
            this.addPanelForTask(t);
        } else {
            this.removePanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }


    private int calculateTotalHeight() {
        int totalHeight = this.model.getAllTask().size() * 60;
        return totalHeight;
    }
    
    private void setListTaskPreferredSize(int totalHeight) {
        // Définir la taille préférée en fonction de la hauteur totale
        this.setPreferredSize(new Dimension(200, totalHeight));
    }

    private void removePanelForTask(Task t) {
        this.removeControllerAboutTask(t);
        this.remove(this.panels.get(t));
        this.removeButton.remove(t);
        this.subPanel.remove(this.panels.get(t));
        this.panels.remove(t);
    }

    private void removeControllerAboutTask(Task task) {
        for (ActionListener al : this.removeButton.get(task).getActionListeners()) {
            this.removeButton.get(task).removeActionListener(al);
        }

        for (MouseListener ml : this.removeButton.get(task).getMouseListeners()) {
            this.removeButton.get(task).removeMouseListener(ml);
        }

        for (MouseListener ml : this.panels.get(task).getMouseListeners()) {
            JPanel p = this.panels.get(task);
            p.removeMouseListener(ml);

            for (MouseListener ml2 : this.subPanel.get(p).getMouseListeners()) {
                this.subPanel.get(p).removeMouseListener(ml2);
            }
        }
    }

    private void initializeComponent() {
        this.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.setBackground(COLOR_NULL);

        Border redBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        Border newBorder = BorderFactory.createCompoundBorder(redBorder, this.getBorder());
        this.setBorder(newBorder);
    }

    public void removeAllControllers() {
        this.model.removePropertyChangeListener(SetTask.PROP_ADD_TASKS, null);
        this.model.removePropertyChangeListener(SetTask.PROP_REMOVE_TASKS, null);
    }

    private void removeAllComponents() {
        Set<Task> taskSet = new HashSet<>(this.panels.keySet());
        for (Task t : taskSet) {
            this.removePanelForTask(t);
        }
        this.revalidate();
        this.repaint();
    }

    private void createComponents() {
        for (Task t : this.model.getAllTask()) {
            this.addPanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }

    private void addPanelForTask(Task t) {
        JPanel p = this.createTaskComponent(t);
        this.add(p);
        this.panels.put(t, p);
    }

    private JPanel createTaskComponent(Task task) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel.setMinimumSize(new Dimension(this.getWidth(), 50));

        JPanel q = new JPanel();

        JLabel description = new JLabel(task.getDescription());
        description.setOpaque(false);
        q.add(description);

        panel.add(q);
        panel.add(this.createRemoveButton(task));
        this.subPanel.put(panel, q);


        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.addMouseListener(new ReactionItemTask(panel));
        panel.addMouseListener(new ReactionItemTask(q));

        return panel;
    }

    private JButton createRemoveButton(Task t) {

        ImageIcon img = new ImageIcon(PATH_ASSET + "remove_task.png");
        Image image = img.getImage();
        Image transform = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton remove = new JButton(new ImageIcon(transform));
    
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        remove.addActionListener(new RemoveTask(this.model, t));
        remove.addMouseListener(new RemoveTask(remove));

        this.removeButton.put(t, remove);
        return remove;
    }

}
