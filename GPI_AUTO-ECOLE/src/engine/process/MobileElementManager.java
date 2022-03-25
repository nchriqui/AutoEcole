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

	private boolean lastMoveUp = true;
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

	public boolean moveLeftCar() {
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
			callCheckClignotants();
			return true;
		}
		return false;
	}

	public boolean moveRightCar() {
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
			callCheckClignotants();
			return true;
		}
		return false;
	}

	public boolean moveDownCar() {
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
			callCheckClignotants();
			return true;
		}
		return false;
	}

	public boolean moveUpCar() {
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
			callCheckClignotants();
			return true;
		}
		return false;
	}

	// Check if the move is possible
	public boolean CheckMove(List<Road> RoadsList, Block block) {
		int carDirection = car.recupDirection();
		boolean verif = false;
		for (Road r : RoadsList) {
			int roadDirection = r.getDirection();
			boolean directionVerif = (carDirection == roadDirection);
			if (r.getPosition().compareBlock(block)) {
				if (roadDirection == 0 || directionVerif) {
					verif = true;
				}
				if (!directionVerif && roadDirection != 0) {
					System.out.println("\nLA ROUTE QUE VOUS VOULEZ EMPRUNTER EST A CONTRE SENS ");
					System.out.println("SCORE -1\n");
					GameConfiguration.SCORE--;
				}
			}
		}
		return verif;
	}

	/*
	 * Check the move, when the car pass on a pedestrian , to decrement the score
	 * just one time
	 */
	public void moveAuto() {
		Block carPosition = car.getPosition();
		int direction = car.recupDirection();
		
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
	
	public boolean checkWaitStop() {
		MapBuilder mapBuilder = new MapBuilder(map.getLineCount(), map.getColumnCount());
		Block carPosition = car.getPosition();
		List<Block> allWaitStop = new ArrayList<Block>();
		allWaitStop.addAll(mapBuilder.getWaitStopH());
		allWaitStop.addAll(mapBuilder.getWaitStopV());

		for (Block waitStopX : allWaitStop) {
			if (carPosition.compareBlock(waitStopX)) {
				return true;
			}
		}
		return false;
	}

	public void callCheckClignotants() {
		if (lastMoveDown || lastMoveUp) {
			if (car.recupDirection() == 1) { // si deplacement gauche
				checkClignotants("gauche");
			}
			if (car.recupDirection() == 3) { // si deplacement droit
				checkClignotants("droit");
			}
		}

		if (lastMoveRight) {
			if (car.recupDirection() == 2) { // si deplacement haut
				checkClignotants("gauche");
			}
			if (car.recupDirection() == 4) { // si deplacement bas
				checkClignotants("droit");
			}
		}

		if (lastMoveLeft) {
			if (car.recupDirection() == 2) { // si deplacement haut
				checkClignotants("droit");
			}
			if (car.recupDirection() == 4) {// si deplacement bas
				checkClignotants("gauche");
			}
		}
	}

	public void checkClignotants(String clignotant) {

		switch (clignotant) {
		case ("gauche"):
			if (car.isClignotantGauche()) {
				System.out.println("VOUS AVEZ ACTIVE LE CLIGONANT GAUCHE AVANT DE TOURNER \n");
			} else {
				System.out.println("VOUS N'AVEZ PAS ACTIVE LE CLIGONANT GAUCHE AVANT DE TOURNER, SCORE-1 \n");
				GameConfiguration.SCORE--;
			}
			break;

		case ("droit"):
			if (car.isClignotantDroit()) {
				System.out.println("VOUS AVEZ ACTIVE LE CLIGONANT DROIT AVANT DE TOURNER \n");
			} else {
				System.out.println("VOUS N'AVEZ PAS ACTIVE LE CLIGONANT DROIT AVANT DE TOURNER, SCORE-1 \n");
				GameConfiguration.SCORE--;
			}
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + clignotant);

		}
	}
	
	public void changeSpeed(int vitesse) {
		car.shifter(vitesse);
	}
}
