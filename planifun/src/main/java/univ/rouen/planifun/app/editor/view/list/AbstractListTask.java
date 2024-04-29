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
import javax.swing.border.Border;
import univ.rouen.planifun.app.editor.model.task.Task;

public abstract class AbstractListTask extends JPanel {

    // CONSTANTS

    public static final Color COLOR_NULL = Color.darkGray;
    public static Color COLOR_NOT_NULL = Color.white;
    public static String PATH_ASSET = "planifun/src/main/resources/assets/";

    // ATTRIBUTES

    protected Map<Task, JPanel> panels;
    protected Map<JPanel, JPanel> subPanel;
    protected Map<Task, JButton> removeButton;
    protected Map<Task, JLabel> descTask;

    // COMMANDS

    public void changeBackgroundColorAboutTask(Task t) {
        this.changeBackgroundColorAboutProgress(this.panels.get(t), t);
    }

    // UTILS

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
    
    protected void setListTaskPreferredSize(int totalHeight) {
        this.setPreferredSize(new Dimension(200, totalHeight));
    }

    protected void removePanelForTask(Task t) {
        this.removeControllerAboutTask(t);
        this.remove(this.panels.get(t));
        this.removeButton.remove(t);
        this.subPanel.remove(this.panels.get(t));
        this.panels.remove(t);
    }

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

    protected void initializeComponent() {
        this.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(COLOR_NULL);

        Border border = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        Border newBorder = BorderFactory.createCompoundBorder(border, this.getBorder());
        this.setBorder(newBorder);
    }

    protected void removeAllComponents(Set<Task> tasks) {
        for (Task t : tasks) {
            this.removePanelForTask(t);
        }
        this.revalidate();
        this.repaint();
    }

    protected void addPanelForTask(Task t) {
        JPanel p = this.createTaskComponent(t);
        this.add(p);
        this.panels.put(t, p);
    }

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

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.changeBackgroundColorAboutProgress(panel, task);

        return panel;
    }

    protected void changeBackgroundColorAboutProgress(JPanel p, Task t) {
        JPanel q = this.subPanel.get(p);
        if (t.getProgressStatus() == 100.0) {
            p.setBackground(Color.green);
            q.setBackground(Color.green);
        } else if (t.getProgressStatus() < 100.0 && t.getProgressStatus() >= 50.0) {
            p.setBackground(Color.orange);
            q.setBackground(Color.orange);
        } else {
            p.setBackground(Color.red);
            q.setBackground(Color.red);
        }
    }

    // ABSTRACT METHODS

    protected abstract int calculateTotalHeight();
    protected abstract JLabel createLabelForDescription(Task task);
    protected abstract JButton createRemoveButton(Task task);

}
