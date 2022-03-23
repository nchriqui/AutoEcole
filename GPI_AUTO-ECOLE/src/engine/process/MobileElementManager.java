package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;

public class MobileElementManager {
	private Map map;
	private Car car;

	private boolean lastMoveUp = false;
	private boolean lastMoveDown = false;
	private boolean lastMoveRight = false;
	private boolean lastMoveLeft = false;

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

	public void moveLeftCar() {
		Block position = car.getPosition();
		Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);

		List<Road> RoadsList = new ArrayList<Road>();
		MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
		RoadsList = mapBuilder.getRoads();

		boolean verif = CheckMove(RoadsList, newPosition);

		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveUp || lastMoveDown || lastMoveLeft) && verif) {
			car.setLastPosition(position);
			car.setPosition(newPosition);
		}
	}

	public void moveRightCar() {
		Block position = car.getPosition();
		Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);

		List<Road> RoadsList = new ArrayList<Road>();
		MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
		RoadsList = mapBuilder.getRoads();

		boolean verif = CheckMove(RoadsList, newPosition);

		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveUp || lastMoveDown || lastMoveRight) && verif) {
			car.setLastPosition(position);
			car.setPosition(newPosition);
		}
	}

	public void moveDownCar() {
		Block position = car.getPosition();
		Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());

		List<Road> RoadsList = new ArrayList<Road>();
		MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
		RoadsList = mapBuilder.getRoads();

		boolean verif = CheckMove(RoadsList, newPosition);
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveRight || lastMoveLeft || lastMoveDown) && verif) {
			car.setLastPosition(position);
			car.setPosition(newPosition);
		}
	}

	public void moveUpCar() {
		Block position = car.getPosition();
		Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());

		List<Road> RoadsList = new ArrayList<Road>();
		MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
		RoadsList = mapBuilder.getRoads();

		boolean verif = CheckMove(RoadsList, newPosition);
		// DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if ((lastMoveRight || lastMoveLeft || lastMoveUp) && verif) {
			car.setLastPosition(position);
			car.setPosition(newPosition);
		}
	}

	// Check if the move is possible
	public boolean CheckMove(List<Road> RoadsList, Block block) {
		boolean verif = false;
		for (Road r : RoadsList) {
			if (r.getPosition().compareBlock(block)) {
				verif = true;
			}
		}
		return verif;
	}

	/*
	 * Check the move, when the car pass on a pedestrian , to decrement the score
	 * just one time
	 */
	public void pedestrianMove(Car car, int direction) {
		Block carPosition = car.getPosition();
		switch (direction) {
		case 1: { // gauche
			car.setPosition(new Block(carPosition.getLine(), carPosition.getColumn() - 1));
			break;
		}
		case 2: { // haut
			car.setPosition(new Block(carPosition.getLine() - 1, carPosition.getColumn()));
			break;
		}
		case 3: { // droite
			car.setPosition(new Block(carPosition.getLine(), carPosition.getColumn() + 1));
			break;
		}
		case 4: { // bas
			car.setPosition(new Block(carPosition.getLine() + 1, carPosition.getColumn()));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}

}
