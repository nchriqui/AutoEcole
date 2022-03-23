package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.JPanel;

public class ImageGUI extends JPanel implements Serializable {

    private static final long serialVersionUID = 1L;

    Image image = null;

    public ImageGUI(Image image) {
        this.image = image;
    }

    public ImageGUI() {
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage(Image image) {
        return image;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int height = this.getSize().height;
            int width = this.getSize().width;
            // g.drawImage(image, 0, 0, this); //use image size
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}
