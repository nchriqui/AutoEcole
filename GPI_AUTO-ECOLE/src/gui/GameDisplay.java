package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import engine.process.MobileElementManager;
import engine.map.Map;
import engine.mobile.Car;

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
		paintStrategy.paint(car, g);

	}

}
