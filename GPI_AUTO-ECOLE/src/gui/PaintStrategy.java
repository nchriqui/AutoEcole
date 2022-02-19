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

	private Image redLeftLight;
	private Image greenLeftLight;

	private Image redRightLight;
	private Image greenRightLight;

	public PaintStrategy() {
		// RECUPERATION DES DIFFERENTES IMAGES DE NOTRE VOITURE ( UNE DIRECTION = UNE
		// IMAGE )
		image = GameUtility.readImage("src/images/mycarr3.png");
		imageUp = GameUtility.readImage("src/images/mycarr.png");
		imageRight = GameUtility.readImage("src/images/mycarr3.png");
		imageLeft = GameUtility.readImage("src/images/mycarr2.png");
		imageDown = GameUtility.readImage("src/images/mycarr4.png");

		redLeftLight = GameUtility.readImage("src/Images/redLight.png");
		greenLeftLight = GameUtility.readImage("src/Images/greenLight.png");

		redRightLight = GameUtility.readImage("src/Images/redLight.png");
		greenRightLight = GameUtility.readImage("src/Images/greenLight.png");
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

	public Image getRedLeftLight() {
		return redLeftLight;
	}

	public void setRedLeftLight(Image redLeftLight) {
		this.redLeftLight = redLeftLight;
	}

	public Image getGreenLeftLight() {
		return greenLeftLight;
	}

	public void setGreenLeftLight(Image greenLeftLight) {
		this.greenLeftLight = greenLeftLight;
	}

	public Image getRedRightLight() {
		return redRightLight;
	}

	public void setRedRightLight(Image redRightLight) {
		this.redRightLight = redRightLight;
	}

	public Image getGreenRightLight() {
		return greenRightLight;
	}

	public void setGreenRightLight(Image greenRightLight) {
		this.greenRightLight = greenRightLight;
	}

	// DESSIN DE LA CARTE DE JEU (DASHBOARD)
	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		// PARCOURS DE LA MAP
		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				// DESSIN DU FOND DE LA CARTE
				graphics.setColor(Color.decode("#005400"));
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);

			}
		}
	}

	// DESSIN D'UN BLOCK DE LA ROUTE
	public void paintRoad(Road road, Graphics graphics) {

		Block position = road.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.GRAY);
		graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);

	}

	public void paintLeftLight(Graphics graphics, Light light) {
		Block position = light.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();
		graphics.drawImage(redLeftLight, x * blockSize, y * blockSize, null);

//		graphics.setColor(Color.RED);
//		graphics.fillRoundRect(x* blockSize, y * blockSize, blockSize, blockSize, blockSize, blockSize);
	}

	public void paintRightLight(Graphics graphics, Light light) {
		Block position = light.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();
		graphics.drawImage(redRightLight, x * blockSize, y * blockSize, null);

//		graphics.setColor(Color.RED);
//		graphics.fillRoundRect(x* blockSize, y * blockSize, blockSize, blockSize, blockSize, blockSize);
	}

	// DESSIN DE LA VOITURE (IMAGE)
	public void paint(Car car, Graphics graphics) {

		Block position = car.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.drawImage(image, x * blockSize, y * blockSize, null);

	}
}
