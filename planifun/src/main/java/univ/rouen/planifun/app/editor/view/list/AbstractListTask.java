package univ.rouen.planifun.app.editor.view.list;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Manage display list task
 */
public abstract class AbstractListTask extends JPanel {

    // CONSTANTS

    /**
     * Color when model is null
     */
    public static final Color COLOR_NULL = Color.darkGray;

    /**
     * Color when model is not null
     */
    public static Color COLOR_NOT_NULL = Color.white;

    // ATTRIBUTES

    protected Map<Task, JPanel> panels;
    protected Map<JPanel, JPanel> subPanel;
    protected Map<Task, JButton> removeButton;
    protected Map<Task, JLabel> descTask;

    // COMMANDS

    /**
     * changeBackgroundColorAboutTask : change color of t
     * @param t : task
     */
    public void changeBackgroundColorAboutTask(Task t) {
        JPanel p = this.panels.get(t);
        JPanel q = this.subPanel.get(p);

        Double progress = t.getProgressStatus();
        Color c = null;
        if (progress >= 0.0 && progress < 10.0) {
            c =  new Color(98, 4, 10);
        } else if (progress >= 10.0 && progress < 20.0) {
            c = new Color(157, 6, 16);
        } else if (progress >= 20.0 && progress < 30.0) {
            c = new Color(208, 0, 0);
        } else if (progress >= 30.0 && progress < 40.0) {
            c = new Color(255, 103, 42);
        } else if (progress >= 40.0 && progress < 50.0) {
            c = new Color(255, 134, 55);
        } else if (progress >= 50.0 && progress < 60.0) {
            c = new Color(249, 160, 63);
        } else if (progress >= 60.0 && progress < 70.0) {
            c = new Color(219, 244, 107);
        } else if (progress >= 70.0 && progress < 80.0) {
            c = new Color(161, 195, 73);
        } else if (progress >= 80.0 && progress < 90.0) {
            c = new Color(138, 170, 36);
        } else if (progress >= 90.0 && progress < 100.0) {
            c = new Color(124, 181, 24);
        } else {
            c = new Color (0, 186, 0);
        }
        p.setBackground(c);
        q.setBackground(c);
    }

    /**
     * updateListAboutTask : update interface after added or remove task
     * @param added : indicate task is added or not
     * @param t : task
     */
    protected void updateListAboutTask(boolean added, Task t) {
        if (added) {
            this.addPanelForTask(t);
        } else {
            this.removePanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }

    /**
     * setListTaskPreferredSize : change height's container
     * @param totalHeight : height
     */
    protected void setListTaskPreferredSize(int totalHeight) {
        this.setPreferredSize(new Dimension(200, totalHeight));
    }

    /**
     * removePanelForTask : remove task from interface
     * @param t : task
     */
    protected void removePanelForTask(Task t) {
        this.removeControllerAboutTask(t);
        this.remove(this.panels.get(t));
        this.removeButton.remove(t);
        this.subPanel.remove(this.panels.get(t));
        this.panels.remove(t);
    }

    /**
     * removeControllerAboutTask : remove controller on this task
     * @param task : task
     */
    protected void removeControllerAboutTask(Task task) {
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

    /**
     * initializeComponent : initialize this container for list task
     */
    protected void initializeComponent() {
        this.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(COLOR_NULL);
    }

    /**
     * removeAllComponents : remove all component's belong to task
     * @param tasks : list task
     */
    protected void removeAllComponents(Set<Task> tasks) {
        for (Task t : tasks) {
            this.removePanelForTask(t);
        }
        this.revalidate();
        this.repaint();
    }

    /***
     * addPanelForTask : add container task in this container
     * @param t : task
     */
    protected void addPanelForTask(Task t) {
        JPanel p = this.createTaskComponent(t);
        this.add(p);
    }

    // UTILS

    /**
     * createTaskComponent : create container for task
     * @param task : task
     * @return JPanel
     */
    private JPanel createTaskComponent(Task task) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel.setMinimumSize(new Dimension(this.getWidth(), 50));

        JPanel q = new JPanel();
        q.add(this.createLabelForDescription(task));

        panel.add(q);
        JButton remove = this.createRemoveButton(task);
        panel.add(remove);

        this.subPanel.put(panel, q);
        this.removeButton.put(task, remove);
        this.panels.put(task, panel);

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.changeBackgroundColorAboutTask(task);

        return panel;
    }

    // ABSTRACT METHODS

    /**
     * calculateTotalHeight : calculate total height for this container
     * @return int
     */
    protected abstract int calculateTotalHeight();

    /**
     * createLabelForDescription : create component for display description
     * @param task : task
     * @return JLabel
     */
    protected abstract JLabel createLabelForDescription(Task task);

    /**
     * createRemoveButton : create button for remove this
     * @param task : task
     * @return JButton
     */
    protected abstract JButton createRemoveButton(Task task);
}