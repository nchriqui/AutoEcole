package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Aircraft;
import engine.mobile.Bomb;
import engine.mobile.Enemy;
import engine.mobile.Missile;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class PaintStrategy {
	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				if ((lineIndex + columnIndex) % 2 == 0) {
					graphics.setColor(Color.GRAY);
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				}
			}
		}
	}

	public void paint(Aircraft aircraft, Graphics graphics) {
		Block position = aircraft.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.BLUE);
		graphics.drawLine(x * blockSize + blockSize / 2, y * blockSize, x * blockSize, (y + 1) * blockSize);
		graphics.drawLine(x * blockSize + blockSize / 2, y * blockSize, (x + 1) * blockSize, (y + 1) * blockSize);
		graphics.drawLine(x * blockSize + blockSize / 2, y * blockSize, x * blockSize + blockSize / 2, (y + 1) * blockSize);

	}

	public void paint(Enemy enemy, Graphics graphics) {
		Block position = enemy.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.RED);
		graphics.fillOval(x * blockSize, y * blockSize, blockSize, blockSize);
	}

	public void paint(Missile missile, Graphics graphics) {
		Block position = missile.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.PINK);
		graphics.fillRect(x * blockSize + blockSize / 3, y * blockSize, blockSize / 3, blockSize);
	}

	public void paint(Bomb bomb, Graphics graphics) {
		Block position = bomb.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.MAGENTA);
		graphics.fillRect(x * blockSize + blockSize / 3, y * blockSize, blockSize / 3, blockSize);
	}

	public void paint(List<Block> bombZone, Graphics graphics) {
		for (Block block : bombZone) {

			int blockSize = GameConfiguration.BLOCK_SIZE;

			int y = block.getLine();
			int x = block.getColumn();

			graphics.setColor(Color.ORANGE);
			graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
		}
	}
}
