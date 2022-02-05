package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import gui.PaintStrategy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import config.GameConfiguration;

public class MobileElementManager {
	private Map map;
	private Car car;

	public MobileElementManager(Map map) {
		super();
		this.map = map;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void moveRightCar() {
		Block position = car.getPosition();
		int sizeBock = GameConfiguration.BLOCK_SIZE;
		if (position.getColumn() < GameConfiguration.COLUMN_COUNT - 2) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			car.setPosition(newPosition);
		}
	}

	public void moveLeftCar() {
		Block position = car.getPosition();
		int sizeBock = GameConfiguration.BLOCK_SIZE;
		if (position.getColumn() > 0) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			car.setPosition(newPosition);
		}
	}

	public void moveDownCar() {
		Block position = car.getPosition();
		int sizeblock = GameConfiguration.BLOCK_SIZE;
		if (position.getLine() < GameConfiguration.LINE_COUNT - 2) {
			Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

	public void moveUpCar() {
		Block position = car.getPosition();
		if (position.getLine() > 0) {
			Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

}
