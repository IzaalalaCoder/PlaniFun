package univ.rouen.planifun.app.editor.view.list;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import univ.rouen.planifun.app.editor.controller.list.ControlRemoveSubTask;
import univ.rouen.planifun.app.editor.controller.list.ControlTitleSubTaskInItem;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.list.task.DisplayComplexTask;

public class ListSubTask extends AbstractListTask {

    // ATTRIBUTES

    private ComplexTask model;
    private DisplayComplexTask parent;
    private Calendar calendar;

    // CONSTRUCTORS

    public ListSubTask(DisplayComplexTask parent, Calendar c) {
        this.parent = parent;
        this.calendar = c;
        this.panels = new HashMap<>();
        this.removeButton = new HashMap<>();
        this.subPanel = new HashMap<>();
        this.descTask = new HashMap<>();
        this.createModel(parent.getModel());
        this.initializeComponent();
    }

    // UTILS

    private void createModel(ComplexTask model) {
        if (this.model != null) {
            this.removeAllControllers();
            this.removeAllComponents();
        }
        
        this.model = model;

        this.createController();
        this.createComponents();
    }

    private void createController() {
        this.model.addPropertyChangeListener(ComplexTask.PROP_ADD_SUB_TASKS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(true, (Task) evt.getNewValue());
            }
        
        });

        this.model.addPropertyChangeListener(ComplexTask.PROP_REMOVE_SUB_TASKS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(false, (Task) evt.getNewValue());
            }
        
        });
    } 

    public void refresh() {
        for (Task t : this.model.getAllSubTasks()) {
            this.changeBackgroundColorAboutProgress(this.panels.get(t), t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }
    
    public void removeAllControllers() {
        this.model.removePropertyChangeListener(ComplexTask.PROP_ADD_SUB_TASKS, null);
        this.model.removePropertyChangeListener(ComplexTask.PROP_REMOVE_SUB_TASKS, null);
    }

    @Override
    protected int calculateTotalHeight() {
        int totalHeight = this.model.getAllSubTasks().size() * 60;
        return totalHeight;
    }

    private void removeAllComponents() {
        Set<Task> taskSet = new HashSet<>(this.panels.keySet());
        super.removeAllComponents(taskSet);
    }

    private void createComponents() {
        for (Task t : this.model.getAllSubTasks()) {
            this.addPanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.refresh();
        this.revalidate();
        this.repaint();
    }

    @Override
    protected JLabel createLabelForDescription(Task task) {
        JLabel description = new JLabel(task.getDescription());
        description.setOpaque(false);
        this.descTask.put(task, description);

        description.addMouseListener(new ControlTitleSubTaskInItem(parent, task, this.calendar));
        return description;
    }

    @Override
    protected JButton createRemoveButton(Task t) {
        ImageIcon img = new ImageIcon(PATH_ASSET + "remove_task.png");
        Image image = img.getImage();
        Image transform = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton remove = new JButton(new ImageIcon(transform));
    
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        remove.addActionListener(new ControlRemoveSubTask(this.model, t));
        remove.addMouseListener(new ControlRemoveSubTask(remove));

        return remove;
    }

}
