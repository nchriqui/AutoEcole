package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.MapBuilder;
import engine.process.MobileElementManager;

public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 2L;

	private Map map;
	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);

	public GameDisplay(Map map, MobileElementManager manager) {
		this.map = map;
		this.manager = manager;
	}

	public PaintStrategy getPaintStrategy() {
		return paintStrategy;
	}

	public void setPaintStrategy(PaintStrategy paintStrategy) {
		this.paintStrategy = paintStrategy;
	}

	@Override

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(map, g);

		for (Road road : mapBuilder.getRoads()) {
			paintStrategy.paintRoad(road, g);
		}

		for (Light lightX : mapBuilder.getLights()) {
			paintStrategy.paintRedLight(lightX, g);
			paintStrategy.paintOrangeLight(lightX, g);
			paintStrategy.paintGreenLight(lightX, g);
		}

		for (Block pedestianV : mapBuilder.getpVCrossings()) {
			paintStrategy.paintVerticalPedestrian(pedestianV, g);
		}

		for (Block pedestianH : mapBuilder.getpHCrossings()) {
			paintStrategy.paintHorizontalPedestian(pedestianH, g);
		}

		for (Block stopX : mapBuilder.getStops()) {
			paintStrategy.paintStop(stopX, g);
		}

		for (Block prohibitedDirX : mapBuilder.getProhibitedDirs()) {
			paintStrategy.paintProhibitedDir(prohibitedDirX, g);
		}

		Car car = manager.getCar();
		paintStrategy.paint(car, g);

	}

	public void checkLight(Car car) {
		Block carPosition = car.getPosition();
		int direction = carPosition.recupDirection(car.getLastPosition());

		for (Block pedestianV : mapBuilder.getpVCrossings()) {
			if (carPosition.compareBlock(pedestianV)) {
				if (paintStrategy.isLightRed()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
					System.out.println("SCORE-2");
				}
				if (paintStrategy.isLightOrange()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
					System.out.println("SCORE-1");
				}
				/* We move the car for leave out the condition */
				manager.pedestrianMove(car, direction);
			}
		}

		for (Block pedestianH : mapBuilder.getpHCrossings()) {
			if (carPosition.compareBlock(pedestianH)) {
				if (paintStrategy.isLightRed()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
					System.out.println("SCORE-2");
				}
				if (paintStrategy.isLightOrange()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
					System.out.println("SCORE-1");
				}
				/* We move the car for leave out the condition */
				manager.pedestrianMove(car, direction);
			}
		}
	}
	
	public Color checkColor(PaintStrategy paint) {
		if (paint.isLightRed()) {
			return Color.red;
		} else if (paint.isLightOrange()) {
			return Color.orange;
		} else {
			return Color.green;
		}
	}

	public void nextRound(int turnNumber) {
		
		if (turnNumber % 5000 == 0 && turnNumber > 1) {
			if (checkColor(paintStrategy) == Color.orange) {
				paintStrategy.setLightRed(true);
				paintStrategy.setLightGreen(false);
				paintStrategy.setLightOrange(false);
				setPaintStrategy(paintStrategy);
			} else if (checkColor(paintStrategy) == Color.red) {
				paintStrategy.setLightRed(false);
				paintStrategy.setLightGreen(true);
				paintStrategy.setLightOrange(false);
				setPaintStrategy(paintStrategy);
			} else {
				paintStrategy.setLightRed(false);
				paintStrategy.setLightGreen(false);
				paintStrategy.setLightOrange(true);
				setPaintStrategy(paintStrategy);
			}
		}
	}
}
