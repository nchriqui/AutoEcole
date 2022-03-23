package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.GameUtility;

public class PaintStrategy extends JPanel {

    private static final long serialVersionUID = 2L;

    private Image image;
    private Image imageUp;
    private Image imageRight;
    private Image imageLeft;
    private Image imageDown;

    private Image imagePedestanVertical;
    private Image imagePedestanHorizontal;
    private Image imageStop;
    private Image imageProhibitedDir;

    private boolean lightRed = true;
    private boolean lightOrange = false;
    private boolean lightGreen = false;

    public PaintStrategy(int choice) {
        /*
         * RECUPERATION DES DIFFERENTES IMAGES DE NOTRE VOITURE ( UNE DIRECTION = UNE
         * IMAGE )
         */

        if (choice == 1) {
            image = GameUtility.readImage("src/images/mycarr3.png");
            imageUp = GameUtility.readImage("src/images/mycarr.png");
            imageRight = GameUtility.readImage("src/images/mycarr3.png");
            imageLeft = GameUtility.readImage("src/images/mycarr2.png");
            imageDown = GameUtility.readImage("src/images/mycarr4.png");
        } else if (choice == 2) {
            image = GameUtility.readImage("src/images/moto3.png");
            imageUp = GameUtility.readImage("src/images/moto.png");
            imageRight = GameUtility.readImage("src/images/moto3.png");
            imageLeft = GameUtility.readImage("src/images/moto2.png");
            imageDown = GameUtility.readImage("src/images/moto4.png");
        } else {
            image = GameUtility.readImage("src/images/truck3.png");
            imageUp = GameUtility.readImage("src/images/truck.png");
            imageRight = GameUtility.readImage("src/images/truck3.png");
            imageLeft = GameUtility.readImage("src/images/truck2.png");
            imageDown = GameUtility.readImage("src/images/truck4.png");
        }

        imagePedestanVertical = GameUtility.readImage("src/images/passage_pieton-Vertical.png");
        imagePedestanHorizontal = GameUtility.readImage("src/images/passage_pieton-Horizontal.png");
        imageStop = GameUtility.readImage("src/images/stop.png");
        imageProhibitedDir = GameUtility.readImage("src/images/sens_interdit.png");

    }

    // 4 Méthodes pour recuperer l'image associe au deplacement
    public Image getImageUp() {
        return imageUp;
    }

    public Image getImageRight() {
        return imageRight;
    }

    public Image getImageLeft() {
        return imageLeft;
    }

    public Image getImageDown() {
        return imageDown;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isLightRed() {
        return lightRed;
    }

    public void setLightRed(boolean lightRed) {
        this.lightRed = lightRed;
    }

    public boolean isLightOrange() {
        return lightOrange;
    }

    public void setLightOrange(boolean lightYollow) {
        this.lightOrange = lightYollow;
    }

    public boolean isLightGreen() {
        return lightGreen;
    }

    public void setLightGreen(boolean lightGreen) {
        this.lightGreen = lightGreen;
    }

    // DESSIN DE LA CARTE DE JEU (DASHBOARD)
    public void paint(Map map, Graphics g) {
        int blockSize = GameConfiguration.BLOCK_SIZE;
        Block[][] blocks = map.getBlocks();

        // PARCOURS DE LA MAP
        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
                Block block = blocks[lineIndex][columnIndex];

                // DESSIN DU FOND DE LA CARTE
                g.setColor(Color.decode("#353535"));
                g.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);

            }
        }
    }

    // DESSIN D'UN BLOCK DE LA ROUTE
    public void paintRoad(Road road, Graphics g) {

        Block position = road.getPosition();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        int y = position.getLine();
        int x = position.getColumn();

        g.setColor(Color.GRAY);
        g.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);

    }

    // PedestianHorizontalCrossing
    public void paintHorizontalPedestian(Block block, Graphics g) {

        int positionPedestianLine = block.getLine();
        int positionPedestianColumn = block.getColumn();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        g.drawImage(imagePedestanHorizontal, positionPedestianColumn * blockSize, positionPedestianLine * blockSize, blockSize, blockSize, null);
    }

    // PedestrianVerticalCrossing
    public void paintVerticalPedestrian(Block block, Graphics g) {

        int positionPedestianLine = block.getLine();
        int positionPedestianColumn = block.getColumn();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        g.drawImage(imagePedestanVertical, positionPedestianColumn * blockSize, positionPedestianLine * blockSize, blockSize, blockSize, null);
    }

    // DESSIN DE LA VOITURE (IMAGE)
    public void paint(Car car, Graphics g) {

        Block position = car.getPosition();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        int y = position.getLine();
        int x = position.getColumn();

        g.drawImage(image, x * blockSize, y * blockSize, null);

    }

    public void paintRedLight(Light light, Graphics g) {
        if (lightRed) {
            Block position = light.getPosition();
            int blockSize = GameConfiguration.BLOCK_SIZE;

            int y = position.getLine();
            int x = position.getColumn();

            g.setColor(Color.BLACK);
            g.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);

            g.setColor(Color.GRAY);
            g.fillOval(x * blockSize + 2, y * blockSize + 2, blockSize - 4, blockSize - 4);

            g.setColor(Color.red);
            g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
        }
    }

    public void paintOrangeLight(Light light, Graphics g) {

        if (lightOrange) {
            // boolean regarde le main dans la methode run

            Block position = light.getPosition();

            int y = position.getLine();
            int x = position.getColumn();
            int blockSize = GameConfiguration.BLOCK_SIZE;

            g.setColor(Color.BLACK);
            g.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);

            g.setColor(Color.GRAY);
            g.fillOval(x * blockSize + 2, y * blockSize + 2, blockSize - 4, blockSize - 4);

            g.setColor(Color.orange);
            g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
        }
    }

    public void paintGreenLight(Light light, Graphics g) {

        if (lightGreen) {

            Block position = light.getPosition();
            int y = position.getLine();
            int x = position.getColumn();
            int blockSize = GameConfiguration.BLOCK_SIZE;

            g.setColor(Color.BLACK);
            g.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);

            g.setColor(Color.GRAY);
            g.fillOval(x * blockSize + 2, y * blockSize + 2, blockSize - 4, blockSize - 4);

            g.setColor(Color.green);
            g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
        }
    }

    public void paintStop(Block stopX, Graphics g) {
        int positionStopLine = stopX.getLine();
        int positionStopColumn = stopX.getColumn();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        g.drawImage(imageStop, positionStopColumn * blockSize, positionStopLine * blockSize, blockSize, blockSize, null);
    }

    public void paintProhibitedDir(Block prohibitedDir, Graphics g) {
        int prohibitedDirsLine = prohibitedDir.getLine();
        int prohibitedDirsColumn = prohibitedDir.getColumn();

        int blockSize = GameConfiguration.BLOCK_SIZE;

        g.drawImage(imageProhibitedDir, prohibitedDirsColumn * blockSize, prohibitedDirsLine * blockSize, blockSize, blockSize, null);
    }

}
