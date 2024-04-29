package univ.rouen.planifun.app.editor.view.util;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ManageImage {

    // STATIC ATTRIBUTES
    
    public static final String PATH_ASSET = "planifun/src/main/resources/assets/";
    
    // STATIC REQUESTS

    public static ImageIcon getIcon(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return null;
        }
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}