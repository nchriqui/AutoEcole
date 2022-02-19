package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;

public class MobileElementManager {
	private Map map;
	private Car car;

	private boolean lastMoveUp = false;
	private boolean lastMoveDown = false;
	private boolean lastMoveRight = false;
	private boolean lastMoveLeft = false;

	private Light leftLight;
	private Light rightLight;

	public MobileElementManager(Map map) {
		this.map = map;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public boolean isLastMoveUp() {
		return lastMoveUp;
	}

	public void setLastMoveUp(boolean lastMoveUp) {
		this.lastMoveUp = lastMoveUp;
	}

	public boolean isLastMoveDown() {
		return lastMoveDown;
	}

	public void setLastMoveDown(boolean lastMoveDown) {
		this.lastMoveDown = lastMoveDown;
	}

	public boolean isLastMoveRight() {
		return lastMoveRight;
	}

	public void setLastMoveRight(boolean lastMoveRight) {
		this.lastMoveRight = lastMoveRight;
	}

	public boolean isLastMoveLeft() {
		return lastMoveLeft;
	}

	public void setLastMoveLeft(boolean lastMoveLeft) {
		this.lastMoveLeft = lastMoveLeft;
	}

	public Light getLeftLight() {
		return leftLight;
	}

	public void setLeftLight(Light leftLight) {
		this.leftLight = leftLight;
	}

	public Light getRightLight() {
		return rightLight;
	}

	public void setRightLight(Light rightLight) {
		this.rightLight = rightLight;
	}

	public void moveLeftCar() {
		Block position = car.getPosition();
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveUp || lastMoveDown || lastMoveLeft)
				&& ((position.getLine() == 1 || position.getLine() == 2 || position.getLine() == 9
						|| position.getLine() == 10) && (position.getColumn() > 1 && position.getColumn() <= 22))
				|| ((position.getLine() >= 1 && position.getLine() <= 10)
						&& (position.getColumn() == 2 || position.getColumn() == 23))) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			car.setPosition(newPosition);
		}
	}

	public void moveRightCar() {
		Block position = car.getPosition();
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveUp || lastMoveDown || lastMoveRight)
				&& ((position.getLine() == 1 || position.getLine() == 2 || position.getLine() == 9
						|| position.getLine() == 10) && (position.getColumn() >= 1 && position.getColumn() <= 21))
				|| ((position.getLine() >= 1 && position.getLine() <= 10)
						&& (position.getColumn() == 1 || position.getColumn() == 22))) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			car.setPosition(newPosition);
		}
	}

	public void moveDownCar() {
		Block position = car.getPosition();
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveRight || lastMoveLeft || lastMoveDown)
				&& ((position.getLine() == 1 || position.getLine() == 9)
						&& (position.getColumn() >= 1 && position.getColumn() <= 23))
				|| ((position.getLine() >= 1 && position.getLine() <= 9)
						&& ((position.getColumn() >= 1 && position.getColumn() <= 2)
								|| (position.getColumn() >= 22 && position.getColumn() <= 23)))) {
			Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

	public void moveUpCar() {
		Block position = car.getPosition();
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveRight || lastMoveLeft || lastMoveUp)
				&& ((position.getLine() == 2 || position.getLine() == 10)
						&& (position.getColumn() >= 1 && position.getColumn() <= 22))
				|| ((position.getLine() >= 2 && position.getLine() <= 10)
						&& ((position.getColumn() >= 1 && position.getColumn() <= 2)
								|| (position.getColumn() >= 22 && position.getColumn() <= 23)))) {
			Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

}
