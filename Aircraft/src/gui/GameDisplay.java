package gui;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Aircraft;
import engine.mobile.Bomb;
import engine.mobile.Enemy;
import engine.mobile.Missile;
import engine.process.MobileElementManager;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();

	public GameDisplay(Map map, MobileElementManager manager) {
		this.map = map;
		this.manager = manager;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(map, g);

		Aircraft aircraft = manager.getAircraft();
		paintStrategy.paint(aircraft, g);

		for (Enemy enemy : manager.getEnemies()) {
			paintStrategy.paint(enemy, g);
		}

		for (Missile missile : manager.getMissiles()) {
			paintStrategy.paint(missile, g);
		}

		Bomb bomb = manager.getBomb();
		if (bomb != null) {
			paintStrategy.paint(bomb, g);
			List<Block> bombZone = manager.getBombZone();
			paintStrategy.paint(bombZone, g);
		}
	}

	public Block getBombPosition(int x, int y) {
		int line = y / GameConfiguration.BLOCK_SIZE;
		int column = x / GameConfiguration.BLOCK_SIZE;
		return map.getBlock(line, column);
	}

}
