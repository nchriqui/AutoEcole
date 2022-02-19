package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import engine.fixed.Road;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.MobileElementManager;
import engine.process.RoadBuilder;

public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 2L;

	private Map map;
	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private RoadBuilder roadBuilder;

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

		Car car = manager.getCar();
		paintStrategy.paint(car, g);

		Light Leftlight = manager.getLeftLight();
		paintStrategy.paintLeftLight(g, Leftlight);

		Light Rightlight = manager.getRightLight();
		paintStrategy.paintRightLight(g, Rightlight);

	}

}
