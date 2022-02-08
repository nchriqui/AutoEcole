package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import engine.Map.Map;
import engine.process.MobileElementManager;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.mobile.Road;

public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 2L;

	private Map map;
	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

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
		Car car = manager.getCar();
		Graphics2D g2 = (Graphics2D) g;
		for (Road road : map.getRoads()) {
			paintStrategy.paintHorizontalRoad(g2,road);
		}	
		for (Road road2 : map.getRoads2()) {
			paintStrategy.paintRoad(g2, road2);
		}
		for (Road road3 : map.getRoads3()) {
			paintStrategy.paintVerticalRoad(g2, road3);
		}
		for (Road road4 : map.getRoads4()) {
			paintStrategy.paintRoad2(g2, road4);
		}
		/*for (Light light : map.getLights()) {
			paintStrategy.paintLights(g2,light);
		}*/
		paintStrategy.paintLights(g2);
		paintStrategy.paint(car, g2);
	}

}
