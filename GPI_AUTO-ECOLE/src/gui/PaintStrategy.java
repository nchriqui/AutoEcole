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

	private boolean lightRed = true;
	private boolean lightYellow = false;
	private boolean lightGreen = false;

	public PaintStrategy() {
		/*
		 * RECUPERATION DES DIFFERENTES IMAGES DE NOTRE VOITURE ( UNE DIRECTION = UNE
		 * IMAGE )
		 */
		image = GameUtility.readImage("src/images/mycarr3.png");
		imageUp = GameUtility.readImage("src/images/mycarr.png");
		imageRight = GameUtility.readImage("src/images/mycarr3.png");
		imageLeft = GameUtility.readImage("src/images/mycarr2.png");
		imageDown = GameUtility.readImage("src/images/mycarr4.png");

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

	public boolean isLightYellow() {
		return lightYellow;
	}

	public void setLightYellow(boolean lightYollow) {
		this.lightYellow = lightYollow;
	}

	public boolean isLightGreen() {
		return lightGreen;
	}

	public void setLightGreen(boolean lightGreen) {
		this.lightGreen = lightGreen;
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
				graphics.setColor(Color.decode("#353535"));
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

	// DESSIN DES LIGNES DE LA ROUTE
	public void paintRoadLine(Road road, Graphics graphics) {
		Color color = Color.YELLOW;
		Block position = road.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		// LINE FOR HORIZONTAL ROAD
		if (x % 2 == 0
				&& !(x == GameConfiguration.DIFF_ROAD_POSITION - 1 || x == GameConfiguration.DIFF_ROAD_POSITION
						|| x == (GameConfiguration.COLUMN_COUNT - GameConfiguration.DIFF_ROAD_POSITION) - 1
						|| x == GameConfiguration.COLUMN_COUNT - GameConfiguration.DIFF_ROAD_POSITION)
				&& !(y == GameConfiguration.DIFF_ROAD_POSITION
						|| y == GameConfiguration.LINE_COUNT - GameConfiguration.DIFF_ROAD_POSITION)) {
			graphics.setColor(color);
			graphics.fillRect(x * blockSize, y * blockSize + blockSize, blockSize, blockSize / 10);

		}

		// LINE FOR VERTICAL ROAD
		if (y % 2 == 0
				&& !(y == GameConfiguration.DIFF_ROAD_POSITION - 1 || y == GameConfiguration.DIFF_ROAD_POSITION
						|| y == (GameConfiguration.LINE_COUNT - GameConfiguration.DIFF_ROAD_POSITION) - 1
						|| y == (GameConfiguration.LINE_COUNT - GameConfiguration.DIFF_ROAD_POSITION))
				&& !(x == GameConfiguration.DIFF_ROAD_POSITION
						|| x == GameConfiguration.COLUMN_COUNT - GameConfiguration.DIFF_ROAD_POSITION)) {
			graphics.setColor(color);
			graphics.fillRect(x * blockSize + blockSize, y * blockSize, blockSize / 10, blockSize);

		}

		// LINE FOR CORNER ROAD LEFT-UP
		if (x == GameConfiguration.DIFF_ROAD_POSITION && y == GameConfiguration.DIFF_ROAD_POSITION) {
			graphics.setColor(color);
			// Horizontal
			graphics.fillRect(x * blockSize, y * blockSize, blockSize / 2, blockSize / 10);

			// Vertical
			graphics.fillRect(x * blockSize, y * blockSize, blockSize / 10, blockSize / 2);
		}

		// LINE FOR CORNER ROAD RIGHT-UP
		if (x == GameConfiguration.COLUMN_COUNT - GameConfiguration.DIFF_ROAD_POSITION
				&& y == GameConfiguration.DIFF_ROAD_POSITION) {
			graphics.setColor(color);
			// Vertical
			graphics.fillRect(x * blockSize, y * blockSize, blockSize / 10, blockSize / 2);

			// Horizontal
			graphics.fillRect(x * blockSize - blockSize / 2, y * blockSize, blockSize / 2, blockSize / 10);

		}

		// LINE FOR CORNER ROAD RIGHT-DOWN
		if (x == GameConfiguration.COLUMN_COUNT - GameConfiguration.DIFF_ROAD_POSITION
				&& y == GameConfiguration.LINE_COUNT - GameConfiguration.DIFF_ROAD_POSITION) {
			graphics.setColor(color);
			// Vertical
			graphics.fillRect(x * blockSize, y * blockSize - (blockSize / 2), blockSize / 10,
					blockSize / 2 + blockSize / 10);

			// Horizontal
			graphics.fillRect(x * blockSize - blockSize / 2, y * blockSize, blockSize / 2, blockSize / 10);

		}

		// LINE FOR CORNER ROAD LEFT-DOWM
		if (x == GameConfiguration.DIFF_ROAD_POSITION
				&& y == GameConfiguration.LINE_COUNT - GameConfiguration.DIFF_ROAD_POSITION) {
			graphics.setColor(color);
			// Vertical
			graphics.fillRect(x * blockSize, y * blockSize - (blockSize / 2), blockSize / 10, blockSize / 2);

			// Horizontal
			graphics.fillRect(x * blockSize, y * blockSize, blockSize / 2, blockSize / 10);

		}

	}

	// PedestianCrossing
	public void paintPedestianCrossing(Light light, int direction, Graphics graphics) {

		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block position = light.getPosition();
		int y;
		int x;

		switch (direction) {
		case 0:// LIGHT UP
			y = position.getLine() + 1;
			x = position.getColumn();
			graphics.setColor(Color.white);
			for (int i = 0; i < blockSize; i = i + (blockSize / 5)) {
				graphics.fillRect(x * blockSize, y * blockSize + i, blockSize, blockSize / 10);
			}
			break;
		case 1: // LIGHT LEFT
			y = position.getLine();
			x = position.getColumn() - 1;
			graphics.setColor(Color.white);
			for (int i = 5; i < blockSize; i = i + (blockSize / 5)) {
				graphics.fillRect(x * blockSize + i, y * blockSize, blockSize / 10, blockSize);

			}
			break;
		case 2:// LIGHT RIGHT

			y = position.getLine();
			x = position.getColumn() + 1;

			graphics.setColor(Color.white);
			for (int i = 0; i < blockSize; i = i + (blockSize / 5)) {
				graphics.fillRect(x * blockSize + i, y * blockSize, blockSize / 10, blockSize);
			}
			break;
		case 3:// LIGHT BOTTOM

			y = position.getLine() - 1;
			x = position.getColumn();
			graphics.setColor(Color.white);
			for (int i = 5; i < blockSize - (blockSize / 10); i = i + (blockSize / 5)) {
				graphics.fillRect(x * blockSize, y * blockSize + i, blockSize, blockSize / 10);

			}

			break;
		default:
			break;
		}
	}

	// DESSIN DE LA VOITURE (IMAGE)
	public void paint(Car car, Graphics graphics) {

		Block position = car.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.drawImage(image, x * blockSize, y * blockSize, null);

	}

	public void paint(Light light, Graphics g) {

		Block position = light.getPosition();
		// System.out.println("light"+ position);
		int blockSize = GameConfiguration.BLOCK_SIZE;
		int y = position.getLine();
		int x = position.getColumn();

		g.setColor(Color.BLACK);
		g.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);
		g.setColor(Color.GRAY);

		g.fillOval(x * blockSize + 2, y * blockSize + 2, blockSize - 4, blockSize - 4);
	}

	public void paintRedLight(Light light, Graphics g) {
		if (lightRed) {
			Block position = light.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;

			int y = position.getLine();
			int x = position.getColumn();
			g.setColor(Color.red);

			g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
		}
	}

	public void paintYollowLight(Light light, Graphics g) {

		if (lightYellow) {
			// boolean regarde le main dans la methode run
			g.setColor(Color.yellow);
			Block position = light.getPosition();

			int y = position.getLine();
			int x = position.getColumn();
			int blockSize = GameConfiguration.BLOCK_SIZE;

			g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
		}
	}

	public void paintGreenLight(Light light, Graphics g) {

		if (lightGreen) {

			Block position = light.getPosition();
			int y = position.getLine();
			int x = position.getColumn();
			int blockSize = GameConfiguration.BLOCK_SIZE;
			g.setColor(Color.green);

			g.fillOval(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
		}
	}

}
