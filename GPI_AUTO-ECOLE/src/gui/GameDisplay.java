package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.LightBuilder;
import engine.process.MobileElementManager;
import engine.process.RoadBuilder;

public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 2L;

	private Map map;
	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private RoadBuilder roadBuilder;
	private LightBuilder lightBuilder;

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

		roadBuilder = new RoadBuilder(map.getLineCount(), map.getColumnCount());
		for (Road road : roadBuilder.getRoads()) {
			paintStrategy.paintRoad(road, g);
		}

		for (Road road : roadBuilder.getRoads()) {
			paintStrategy.paintRoadLine(road, g);
		}

		lightBuilder = new LightBuilder(map.getLineCount(), map.getColumnCount());
		// System.out.println(lightBuilder.getLightsList().toString());
		for (int i = 0; i < lightBuilder.getLightsList().size(); i++) {
			Light lightX = lightBuilder.getLightsList().get(i);
			paintStrategy.paint(lightX, g);

			paintStrategy.paintRedLight(lightX, g);
			paintStrategy.paintYollowLight(lightX, g);
			paintStrategy.paintGreenLight(lightX, g);
			paintStrategy.paintPedestianCrossing(lightX, i, g);
		}

		Car car = manager.getCar();
		paintStrategy.paint(car, g);

	}

	public void checkLight(Car car) {
		// Condition for the left Light
		if (car.getPosition().getLine() == 5 && car.getPosition().getColumn() == 2) {
			if (paintStrategy.isLightRed()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
				System.out.println("SCORE-2");
			}
			if (paintStrategy.isLightYellow()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
				System.out.println("SCORE-1");
			}
			/* We move the car for leave out the condition */
			car.setPosition(new Block(car.getPosition().getLine() + 1, car.getPosition().getColumn()));
		}

		// Condition for the right Light
		if (car.getPosition().getLine() == 5 && car.getPosition().getColumn() == 22) {
			if (paintStrategy.isLightRed()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
				System.out.println("SCORE-2");
			}
			if (paintStrategy.isLightYellow()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
				System.out.println("SCORE-1");
			}
			/* We move the car for leave out the condition */
			car.setPosition(new Block(car.getPosition().getLine() - 1, car.getPosition().getColumn()));
		}

		// Condition for the up Light
		if (car.getPosition().getLine() == 1
				&& car.getPosition().getColumn() == (GameConfiguration.COLUMN_COUNT - 1) / 2) {
			if (paintStrategy.isLightRed()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
				System.out.println("SCORE-2");
			}
			if (paintStrategy.isLightYellow()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
				System.out.println("SCORE-1");
			}
			/* We move the car for leave out the condition */
			car.setPosition(new Block(car.getPosition().getLine(), car.getPosition().getColumn() - 1));
		}

		// Condition for the bottom Light
		if (car.getPosition().getLine() == GameConfiguration.LINE_COUNT - 2
				&& car.getPosition().getColumn() == (GameConfiguration.COLUMN_COUNT - 1) / 2) {
			if (paintStrategy.isLightRed()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
				System.out.println("SCORE-2");
			}
			if (paintStrategy.isLightYellow()) {
				GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

				System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
				System.out.println("SCORE-1");
			}

			/* We move the car for leave out the condition */
			car.setPosition(new Block(car.getPosition().getLine(), car.getPosition().getColumn() + 1));
		}

	}
}
